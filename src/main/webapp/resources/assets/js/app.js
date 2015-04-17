/**
 * Views
 */

var HomepageView = Backbone.View.extend({
    el: '.page',
    render: function () {
        var that = this;
        var template = _.template($('#homepage').html(), {});
        that.$el.html(template);
    }
});

var homepageView = new HomepageView();

var ContactView = Backbone.View.extend({
    el: '.page',
    render: function () {
        var that = this;
        var template = _.template($('#contact').html(), {});
        that.$el.html(template);
    }
});

var contactView = new ContactView();

var Journeys = Backbone.Collection.extend({
    url: '/api/journeys'
});

var JourneyListView = Backbone.View.extend({
    el: '.page',
    render: function () {
        var that = this;
        var journeys = new Journeys();
        journeys.fetch({
            success: function (journey) {
                var template = _.template($('#journey-list-template').html(), {journeys: journeys.models});
                that.$el.html(template);
            }
        })
    }
});

var journeyListView = new JourneyListView();


var Journey = Backbone.Model.extend({
    urlRoot: '/api/journeys'
});

var JourneyEditView = Backbone.View.extend({
    el: '.page',
    events: {
        'submit .edit-journey-form': 'saveJourney',
        'click .delete': 'deleteJourney'
    },
    saveJourney: function (ev) {
        var journeyDetails = $(ev.currentTarget).serializeObject();
        var journey = new Journey();
        journey.save(journeyDetails, {
            success: function (journey) {
                router.navigate('journeys', {trigger:true});
            }
        });
        return false;
    },
    deleteJourney: function (ev) {
        this.journey.destroy({
            success: function () {
                router.navigate('journeys', {trigger:true});
            }
        })
    },
    render: function (options) {
        var that = this;
        if(options.id) {
            that.journey = new Journey({id: options.id});
            that.journey.fetch({
                success: function (journey) {
                    var template = _.template($('#edit-journey-template').html(), {journey: journey});
                    that.$el.html(template);
                }
            })
        } else {
            var template = _.template($('#edit-journey-template').html(), {journey: null});
            that.$el.html(template);
        }
    }
});

var journeyEditView = new JourneyEditView();


var Users = Backbone.Collection.extend({
    url: '/api/users'
});

var UserListView = Backbone.View.extend({
    el: '.page',
    render: function () {
        var that = this;
        var users = new Users();
        users.fetch({
            success: function (users) {
                var template = _.template($('#user-list-template').html(), {users: users.models});
                that.$el.html(template);
            }
        })
    }
});

var userListView = new UserListView();

var User = Backbone.Model.extend({
    urlRoot: '/api/users'
});

var UserEditView = Backbone.View.extend({
    el: '.page',
    events: {
        'submit .edit-user-form': 'saveUser',
        'click .deleteUser': 'deleteUser'
    },
    saveUser: function (ev) {
        var userDetails = $(ev.currentTarget).serializeObject();
        var user = new User();
        user.save(userDetails, {
            success: function (user) {
                router.navigate('users', {trigger:true});
            }
        });
        return false;
    },
    deleteUser: function (ev) {
        this.user.destroy({
            success: function () {
                router.navigate('users', {trigger:true});
            }
        })
    },
    render: function (options) {
        var that = this;
        if(options.id) {
            that.user = new User({id: options.id});
            that.user.fetch({
                success: function (user) {
                    var template = _.template($('#edit-user-template').html(), {user: user});
                    that.$el.html(template);
                }
            })
        } else {
            var template = _.template($('#edit-user-template').html(), {user: null});
            that.$el.html(template);
        }
    }
});

var userEditView = new UserEditView();

/**
 * Router
 */

var Router = Backbone.Router.extend({
    routes: {
        "": "home",
        "journeys": "journeys",
        "journeys/edit/:id": "editJourney",
        "journeys/new": "editJourney",
        "contact": "contact",
        "users": "users",
        "users/edit/:id": "editUser"

    }
});

var router = new Router;

router.on('route:home', function() {
    homepageView.render();
})
router.on('route:journeys', function() {
    journeyListView.render();
})
router.on('route:editJourney', function(id) {
    journeyEditView.render({id: id});
})
router.on('route:contact', function() {
    contactView.render();
})
router.on('route:users', function() {
    userListView.render();
})
router.on('route:editUser', function(id) {
    userEditView.render({id: id});
})


Backbone.history.start();