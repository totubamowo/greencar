<!-- TEMPLATES -->
<script type="text/template" id="homepage">

    <div class="jumbotron">
        <div class="container">
            <h1>UoL Car Sharing Platform</h1>

            <p>This website is a system that supports a car sharing scheme for people travelling on University-related
                affairs.</p>

            <p>It suggest suitable journey peers to the you. However, you ultimately decide to peer for car sharing.</p>
            <br>

            <p>Login or register add journeys to the system</p>

            <p><a href="login" class="btn btn-primary btn-lg">Login or Register &raquo;</a></p>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <h2>Any journey</h2>

                <p>Need to get to a conference, event or work? Simply run a search on your journey and find others going
                    your way. Create driver journeys so other can find you.</p>

                <p><a class="btn btn-default" href="#">More &raquo;</a></p>
            </div>
            <div class="col-lg-4">
                <h2>Save money</h2>

                <p>Whether you're driving or catching a lift, sharing your journey will save you money - cheaper than
                    the train and most bus trips, what are you waiting for?</p>

                <p><a class="btn btn-default" href="#">More &raquo;</a></p>
            </div>
            <div class="col-lg-4">
                <h2>Have fun</h2>

                <p>We've met some of our best friends through car sharing and had some real fun - your destination
                    provides instant common ground.</p>

                <p><a class="btn btn-default" href="#">More &raquo;</a></p>
            </div>
        </div>

    </div> <!-- /container -->
</script>

<script type="text/template" id="contact">

    <div class="container">

        <h1>Contact Me</h1>

        <p>Let's have a beer.</p>

    </div>

</script>

<script type="text/template" id="journey-list-template">

    <div class="container">

        <h1>Journeys</h1>


        <div class="table-responsive">
            <table class="table table-hover table-striped">
                <tbody>
                <@ _.each(journeys, function(journey) { @>
                <tr>
                    <td>
                        <div class="col-sm-3">
                            <img width="150px" src="https://placeimg.com/600/360/any"
                                 class="img-responsive img-thumbnail">
                        </div>
                        <div class="col-sm-9">
                            <div><strong>Name: </strong><@= htmlEncode(journey.get('user').username) @></div>
                            <div><strong><@= journey.get('driver') ? 'Driver' : 'Rider' @></strong></div>
                            <div><strong>From: </strong><@= htmlEncode(journey.get('source')) @></div>
                            <div><strong>To: </strong><@= htmlEncode(journey.get('sink')) @></div>
                            <div class="pull-right"><a href="/journey/view?id=<@= journey.id @>"
                                                       class="btn btn-info btn-xs">Find
                                out
                                more</a></div>
                        </div>
                    </td>
                </tr>
                <@ }); @>
                </tbody>
            </table>
        </div>

    </div>

</script>

<script type="text/template" id="user-list-template">

    <div class="container">

        <h1>Users</h1>

        <div class="table-responsive">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <@ _.each(users, function(user) { @>
                <tr>
                    <td><@= user.id @></td>
                    <td><@= htmlEncode(user.get('username')) @></td>
                    <td><@= htmlEncode(user.get('firstName')) @></td>
                    <td><@= htmlEncode(user.get('lastName')) @></td>
                    <td><@= htmlEncode(user.get('email')) @></td>
                    <td><a href="/#users/edit/<@= user.id @>" class="btn btn-info btn-xs">Edit</a></td>
                </tr>
                <@ }); @>
                </tbody>
            </table>
        </div>
    </div>

</script>

<script type="text/template" id="edit-user-template">
    <div class="container">
        <form class="edit-user-form">

            <h1><@= user ? 'Edit' : 'New' @> User</h1>

            <input name="id" id="userId" type="hidden" value="<@= user ? user.get('id') : '' @>">

            <div class="form-group">
                <label for="username">Username</label>
                <input name="username" id="username" type="text" class="form-control"
                       value="<@= user ? user.get('username') : '' @>">
            </div>

            <div class="form-group">
                <label for="userFirstName">First Name</label>
                <input name="firstName" id="userFirstName" type="text" class="form-control"
                       value="<@= user ? user.get('firstName') : '' @>">
            </div>

            <div class="form-group">
                <label for="userLastName">Last Name</label>
                <input name="lastName" id="userLastName" type="text" class="form-control"
                       value="<@= user ? user.get('lastName') : '' @>">
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input name="email" id="email" type="text" class="form-control"
                       value="<@= user ? user.get('email') : '' @>">
            </div>

            <div class="form-group">
                <label for="newPassword">New Password</label>
                <input name="newPassword" id="newPassword" type="password" class="form-control">
            </div>

            <hr/>

            <button type="submit" class="btn btn-primary"><@= user ? 'Update' : 'Create' @></button>

        </form>

        <@ if(user) { @>
        <br/>
        <input type="hidden" name="id" value="<@= user.id @>"/>
        <button data-user-id="<@= user.id @>" class="btn btn-danger deleteUser">Delete</button>
        <@ }; @>
    </div>
</script>