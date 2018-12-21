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
                    <button class="btn btn-info btn-block" onclick="setAdminSubView('5','admin/orders/ViewOrders.jsp')">Pending</button>
                </div>
                <div class="col-sm-4">
                    <button class="btn btn-info btn-block" onclick="setAdminSubView('5','admin/orders/ViewApproved.jsp')">Approved</button>
                </div>
                <div class="col-sm-4">
                    <button class="btn btn-info btn-block" onclick="setAdminSubView('5','admin/orders/ViewDelevered.jsp')">Delivery</button>
                </div>
            </div>

            <div class="col-sm-12" id="sview5">
                
            </div>
            
        </div>

    </center>
