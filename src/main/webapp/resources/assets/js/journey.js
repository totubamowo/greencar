// config options
var url = 'http://127.0.0.1:8080/geoserver/greencar/wms';
var clientSRID = 'EPSG:3857';
var serverSRID = 'EPSG:4326';
var serverType = 'geoserver';
var lei = [-126874.25213773156, 6916497.341662836];
var zoom = 12;

try {
    // Create an openLayer map object
    var map = new ol.Map({
        // Map control set up
        /*controls: ol.control.defaults().extend([
         new app.LayersControl({
         groups: {
         background: {
         title: 'Base Layers',
         exclusive: true
         },
         'default': {
         title: 'Overlays'
         }
         }
         })
         ]),*/

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
    alert(err + ' Please contact developer');
}

// Set up roads layer
var roads = new ol.layer.Tile({
    title: 'Roads',
    source: new ol.source.TileWMS({
        url: url,
        params: {'LAYERS': 'greencar:roads'},
        serverType: serverType
    })
});

// Add roads to map layer
map.addLayer(roads);

// Initialize route layer
var route = undefined;

// Source marker stroke
var sourceStroke = new ol.style.Stroke({
    color: 'rgb(0,0,255)',
    width: 5.00
});
// Sink marker stroke
var sinkStroke = new ol.style.Stroke({
    color: 'rgb(255,0,0)',
    width: 5.00
});

// Styles for source marker
var sourceStyles = [
    new ol.style.Style({
        image: new ol.style.Circle({
            stroke: sourceStroke,
            radius: 5
        }),
        stroke: sourceStroke
    })
];
// Styles for sink marker
var sinkStyles = [
    new ol.style.Style({
        image: new ol.style.Circle({
            stroke: sinkStroke,
            radius: 5
        }),
        stroke: sinkStroke
    })
];

// Map Feature for rendering source marker
var source = new ol.Feature();
source.setStyle(sourceStyles);
// Map Feature for rendering sink marker
var sink = new ol.Feature();
sink.setStyle(sinkStyles);

// Add source and sink markers as a VectorLayer on map
var vectorLayer = new ol.layer.Vector({
    source: new ol.source.Vector({
        features: [source, sink]
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
    params.LAYERS = 'greencar:ref_route';
    params.FORMAT = 'image/png';
    params.viewparams = viewparams.join(';');

    //  ol.source object holds the WMS GET parameters that will be sent to GeoServer.
    route = new ol.layer.Image({
        source: new ol.source.ImageWMS({
            url: url,
            params: params,
            serverType: serverType
        })
    });

    // Add route to layer
    map.addLayer(route);
}

// Utility method for converting HTML form to JavaScript Objects
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

var sourceField = $("#source");
var sinkField = $("#sink");

var triggerPoints = function () {
    sourceField.change();
    sinkField.change();
}

var clearRoute = function () {
    // Remove the route layers in map
    if (route !== undefined)
        map.removeLayer(route);
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

// Register a map click listener for source and sink.
map.on('click', function (event) {
    var pty = $('#journey').serializeObject().pointType;
    var geo = transform(event.coordinate);

    if (pty == "s") {
        // Source marker click.
        $.getJSON('http://api.postcodes.io/postcodes?lon=' + geo[0] + '&lat=' + geo[1], function (data) {
            if (null !== data.result) {
                source.setGeometry(new ol.geom.Point(event.coordinate));
                var postcode = data.result[0];
                sourceField.val(postcode.outcode + postcode.incode);
                sourceField.change();
            }
        });

    } else if (pty == "t") {
        // Sink marker click.
        $.getJSON('http://api.postcodes.io/postcodes?lon=' + geo[0] + '&lat=' + geo[1], function (data) {
            if (null !== data.result) {
                sink.setGeometry(new ol.geom.Point(event.coordinate));
                var postcode = data.result[0];
                sinkField.val(postcode.outcode + postcode.incode);
                sinkField.change();
            }
        });
    }
});

sourceField.keyup(function () {
    sourceField.change()
});
sourceField.blur(function () {
    sourceField.change()
});
sinkField.keyup(function () {
    sinkField.change()
});
sinkField.blur(function () {
    sinkField.change()
});

sourceField.change(function () {
    var postcode = $(this).val();
    postcode = $.trim(postcode);
    if (postcode.length >= 5) {
        $.getJSON('http://api.postcodes.io/postcodes/' + postcode, function (data) {
            source.setGeometry(new ol.geom.Point(transform_([data.result.longitude, data.result.latitude])));
            if (sink.getGeometry() !== undefined) {
                clearRoute();
                computeRoute(source.getGeometry().getCoordinates(), sink.getGeometry().getCoordinates());
            }
        }).fail(function () {
            sourceField.addClass('error');
        });
    }
});

sinkField.change(function () {
    var postcode = $(this).val();
    postcode = $.trim(postcode);
    if (postcode.length >= 5) {
        $.getJSON('http://api.postcodes.io/postcodes/' + postcode, function (data) {
            sink.setGeometry(new ol.geom.Point(transform_([data.result.longitude, data.result.latitude])));
            if (source.getGeometry() !== undefined) {
                clearRoute();
                computeRoute(source.getGeometry().getCoordinates(), sink.getGeometry().getCoordinates());
            }
        }).fail(function () {
            sinkField.addClass('error');
        });
    }
});

$(document).ready(triggerPoints);

$("#route-btn").click(triggerPoints);

$('#clear-route').click(clearRoute);

$('#toggle-roads').click(function () {
    var checked = $('#toggle-roads-form').serializeObject();
    if (checked.value == 'on')
        roads.setVisible(true);
    else
        roads.setVisible(false);
});

$('#fly-to-lei').click(flyToLei);