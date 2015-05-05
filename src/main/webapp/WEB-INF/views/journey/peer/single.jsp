<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file='../../header.jsp' %>

<link rel="stylesheet" href="/resources/assets/css/journey.css">

<!-- OpenLayers3 CSS & JS -->
<link rel="stylesheet" href="/resources/assets/js/vendor/ol-v3.2.1/css/ol.css">
<script src="/resources/assets/js/vendor/ol-v3.2.1/build/ol-debug.js" type="text/javascript"></script>

<div class="container">
    <br>

    <h4>
        <a href="#" class="btn" title="view journey on map">
            <i class="fa fa-automobile"></i>&nbsp;&nbsp;
            <c:forEach items="${postcodes}" var="postcode" varStatus="loop">
                ${postcode}&nbsp;&nbsp;<i class="fa fa-arrow-right"></i>&nbsp;&nbsp;
            </c:forEach></a>
        <a href="<%=request.getHeader("referer")%>" class="btn pull-right"><i
                class="fa fa-arrow-left"></i>&nbsp;&nbsp;back</a>
    </h4>

    <div id="map"></div>

    <ul id="map-controls" class="nav navbar-nav navbar-right">
        <li>
            <a id="fly-to-lei" href="#"><i class="fa fa-home"></i>&nbsp;&nbsp;Go to Leicester</a>
        </li>
        <li><a id="zoom-full" href="#"><i class="fa fa-arrows-alt"></i>&nbsp;&nbsp;Zoom
            To Full Extent</a></li>
        <li>
            <a id="toggle-roads" href="#"><i class="fa fa-bullseye"></i>&nbsp;&nbsp;Toggle Roads</a>
        </li>
    </ul>
</div>

<%@include file='../../footer.jsp' %>

<script type="application/javascript">
    // config options
    var url = 'http://127.0.0.1:8080/geoserver/greencar/wms';
    var clientSRID = 'EPSG:3857';
    var serverSRID = 'EPSG:4326';
    var serverType = 'geoserver';
    var lei = [-126874.25213773156, 6916497.341662836];
    var zoom = 15;

    try {
        // Create an openLayer map object
        var map = new ol.Map({
            // Map control set up
            /*controls: ol.control.defaults().extend([new app.LayersControl({groups: {background: {title: 'Base Layers',exclusive: true},'default': {title: 'Overlays'}}})]),*/

            // render the map in the 'map' div
            target: document.getElementById('map'),

            // Use the Canvas renderer
            renderer: 'canvas',

            // Initialize map layers
            layers: [
                // MapQuest streets
                new ol.layer.Tile({
                    title: 'Street Map',
                    group: 'background',
                    source: new ol.source.MapQuest({layer: 'osm'})
                }),
                // MapQuest imagery
                new ol.layer.Tile({
                    title: 'Aerial Imagery',
                    group: 'background',
                    visible: false,
                    source: new ol.source.MapQuest({layer: 'sat'})
                }),
                // MapQuest hybrid (uses a layer group)
                new ol.layer.Group({
                    title: 'Imagery with Streets',
                    group: 'background',
                    visible: false,
                    layers: [
                        new ol.layer.Tile({
                            source: new ol.source.MapQuest({layer: 'sat'})
                        }),
                        new ol.layer.Tile({
                            source: new ol.source.MapQuest({layer: 'hyb'})
                        })
                    ]
                })
            ],
            // Initialize map center and zoom
            view: new ol.View({
                center: lei,
                zoom: zoom
            })
        });
    }
    catch (err) {
        alert(err + '. Please contact developer');
    }

    // Set up roads layer
    var roads = new ol.layer.Tile({
        title: 'Roads',
        source: new ol.source.TileWMS({
            url: url,
            params: {'LAYERS': 'greencar:roads'},
            serverType: serverType
        }),
        visible: false
    });

    // Add roads to map layer
    map.addLayer(roads);

    // Styles for source marker
    var sourceStyles = [
        new ol.style.Style({
            image: new ol.style.Icon({
                anchor: [10, 30],
                anchorXUnits: 'pixels',
                anchorYUnits: 'pixels',
                opacity: 0.75,
                src: '/resources/assets/img/marker-source.png'
            })
        })
    ];

    // Styles for neutral marker
    var neutralStyles = [
        new ol.style.Style({
            image: new ol.style.Icon({
                anchor: [10, 30],
                anchorXUnits: 'pixels',
                anchorYUnits: 'pixels',
                opacity: 0.75,
                src: '/resources/assets/img/marker-neutral.png'
            })
        })
    ];

    // Styles for sink marker
    var sinkStyles = [
        new ol.style.Style({
            image: new ol.style.Icon({
                anchor: [10, 30],
                anchorXUnits: 'pixels',
                anchorYUnits: 'pixels',
                opacity: 0.75,
                src: '/resources/assets/img/marker-sink.png'
            })
        })
    ];

    var postcodeToGeo = function (postcode, feature) {
        $.getJSON('http://api.postcodes.io/postcodes/' + postcode,
                function (data) {
                    window[feature].setGeometry(new ol.geom.Point(transform_([data.result.longitude, data.result.latitude])));
                }).fail(function () {
                    return null;
                }
        );
    }

    // Map Feature for rendering neutral marker
    var markers = [];
    // Length of postcodes
    <c:set var="len" value="${fn:length(postcodes)}"/>

    <c:forEach items="${postcodes}" var="postcode" varStatus="loop">
    var marker${loop.index} = new ol.Feature();
    if (${loop.index == 0}) {
        marker${loop.index}.setStyle(sourceStyles);
    } else if (${loop.index == len-1}) {
        marker${loop.index}.setStyle(sinkStyles);
    } else {
        marker${loop.index}.setStyle(neutralStyles);
    }
    postcodeToGeo('${postcode}', 'marker${loop.index}');
    markers.push(marker${loop.index});
    </c:forEach>

    // Add source and sink markers as a VectorLayer on map
    var vectorLayer = new ol.layer.Vector({
        source: new ol.source.Vector({
            features: markers
        })
    });

    map.addLayer(vectorLayer);

    // Utility function for converting coordinates from EPSG:3857 to EPSG:4326.
    var transform = ol.proj.getTransform(clientSRID, serverSRID);
    // Utility function for converting coordinates from EPSG:4326 to EPSG:3857.
    var transform_ = ol.proj.getTransform(serverSRID, clientSRID);

    // Compute route by sending a request to GeoServer running on port 8080
    var computeRoute = function (sourcePoint, sinkPoint) {
        // Transform the coordinates
        var sourceCoord = transform(sourcePoint);
        var sinkCoord = transform(sinkPoint);

        var viewparams = [
            'x1:' + sourceCoord[0], 'y1:' + sourceCoord[1],
            'x2:' + sinkCoord[0], 'y2:' + sinkCoord[1]
        ];

        var params = new Object();
        params.LAYERS = 'greencar:shortest_path';
        params.FORMAT = 'image/png';
        params.viewparams = viewparams.join(';');

        //  ol.source object holds the WMS GET parameters that will be sent to GeoServer.
        var route = new ol.layer.Image({
            source: new ol.source.ImageWMS({
                url: url,
                params: params,
                serverType: serverType
            })
        });

        // Add route to layer
        map.addLayer(route);
    }

    for (var i = 0; i < ${len-1}; i++) {
        var source = markers[i];
        var sink = markers[i+1];
        computeRoute(source.getGeometry().getCoordinates(), sink.getGeometry().getCoordinates());
    }

    var toggleRoads = function () {
        roads.setVisible(!roads.getVisible());
    }

    var zoomFull = function () {
        map.getView().setZoom(3);
    }

    var flyToLei = function () {
        var duration = 2000;
        var start = +new Date();
        var pan = ol.animation.pan({
            duration: duration,
            source: /** @type {ol.Coordinate} */ (map.getView().getCenter()),
            start: start
        });
        var bounce = ol.animation.bounce({
            duration: duration,
            resolution: 4 * map.getView().getResolution(),
            start: start
        });
        map.beforeRender(pan, bounce);
        map.getView().setCenter(lei);
        map.getView().setZoom(zoom);
    }

    $('#toggle-roads').click(toggleRoads);

    $('#fly-to-lei').click(flyToLei);

    $('#zoom-full').click(zoomFull);
</script>

</body>
</html>