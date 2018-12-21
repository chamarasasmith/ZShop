        <br>
        <br>
        <h1 class="h1 text-center">User Panel</h1>
        <br>
        <br>
        <br>
        <br>

    <center>
        <div>
            
            <div class="col-sm-12">
                <div class="col-sm-4">
                    <button class="btn btn-info btn-block">My Profile</button>
                </div>
                <div class="col-sm-4">
                    <button class="btn btn-info btn-block" onclick="setAdminSubView('4','admin/user/ViewUsers.jsp')">All User</button>
                </div>
                <div class="col-sm-4">
                    <button class="btn btn-info btn-block" onclick="setAdminSubView('4','admin/user/BlockedUsers.jsp')">Blocked User</button>
                </div>
            </div>

            <div class="col-sm-12" id="sview4">
                
            </div>
            
        </div>

    </center>
