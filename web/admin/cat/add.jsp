       
<form id="cadd1">
            <div class="row">
                <br>
                <img src="img/icon/cat.png" id="vcimg1" style="width: 200px;height: 200px" onclick="document.getElementById('cimg1').click()">
            
            </div>
            <div class="row">
                <br>
                <br>

                <div class="col-sm-1"></div>
                <div class="col-sm-5">

                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                        <input type="text" class="form-control" id="cname1" name="cname1" placeholder="Category Name">
                    </div>
                    <br>

                    <!--<button class="btn btn-danger btn-block">Delete</button>-->

                </div> 

                <div class="col-sm-5">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-file"></i></span>
                        <input type="file" class="form-control" id="cimg1" name="cimg1" onchange="setImg3()"> 
                    </div>
                    <br>

                    <button type="button" class="btn btn-info btn-block" onclick="addCat()">Add</button>
                </div>
                <div class="col-sm-1"></div>


            </div>
            <div>
                <br>
                <div id="alert_cat" class="text-center"></div>
                <br>
            </div>



        </form>
