        <form id="badd1">
            <div class="row">
                <br>
                <img src="img/icon/brand1.png" id="vbimg1" style="width: 200px;height: 200px" onclick="document.getElementById('bimg1').click()">
            </div>
            <div class="row">
                <br>
                <br>

                <div class="col-sm-1"></div>
                <div class="col-sm-5">

                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                        <input type="text" class="form-control" id="bname1" name="bname1" placeholder="Brand Name">
                    </div>
                    <br>

                    <!--<button class="btn btn-danger btn-block">Delete</button>-->

                </div> 

                <div class="col-sm-5">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-file"></i></span>
                        <input type="file" class="form-control" id="bimg1" name="bimg1" onchange="setImg2()"> 
                    </div>
                    <br>

                    <button type="button" class="btn btn-info btn-block" onclick="addBrand()">Add</button>
                </div>
                <div class="col-sm-1"></div>


            </div>
            <div>
                <br>
                <div id="alert_brand" class="text-center"></div>
                <br>
            </div>



        </form>

