        <style>

            .list1{
                background-color: #007bfd; 
                height: 60px; 
                border-color: black; 
                border-width: 4px; 
                border-radius: 10px; 
                font-weight: bold; 
                color: yellow; 
                font-size: medium;
                display:block;
                width:100%;
            }
            .list1:hover{
                background-color: #0054fd; 
            }
            .list1:focus{
                background-color: #0054fd;
            }


        </style>
       
        <br>
    <center>
        <img src="img/z.png" alt="" width="200" height="200" class="img-circle img-thumbnail" onclick="document.getElementById('button5').click()" style="cursor: pointer"/>
    </center>
    
    <p>
<!--        <label for="upload">
            <span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>
            <input type="file" id="upload" style="display: none">
        </label>-->
        <input type="file" name="button5" id="button5" style="display: none">
    </p>

    <hr>
    <hr>

    <div class="list-group">
        <button type="button" class="list1 btn-block" onclick="setAdminView('admin/dash/dashboard1.jsp')"> Dashboard <span class="badge" style="background-color: black"></span></button>
        <button type="button" class="list1 btn-block" onclick="setAdminView('admin/products/products.jsp')"> Products <span class="badge" style="background-color: black"></span></button>
        <button type="button" class="list1 btn-block" onclick="setAdminView('admin/brand1/brand1.jsp')"> Brand <span class="badge" style="background-color: black"></span></button>
        <button type="button" class="list1 btn-block" onclick="setAdminView('admin/cat/category1.jsp')"> Category <span class="badge" style="background-color: black"></span></button>
        <button type="button" class="list1 btn-block" onclick="setAdminView('admin/user/user.jsp')"> Users <span class="badge" style="background-color: black"></span></button>
        <button type="button" class="list1 btn-block" onclick="setAdminView('admin/orders/order.jsp')"> Orders <span class="badge" style="background-color: black"></span></button>
    </div>

    <script>
       
    </script>
