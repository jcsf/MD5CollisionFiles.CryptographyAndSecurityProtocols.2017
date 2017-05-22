<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="<%= request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%= request.getContextPath() %>/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>
    <script src="<%= request.getContextPath() %>/js/functions.js"></script>
    <title>MD5 Collision Files</title>
  </head>
  <body onload="addCodeKeyEvents();">
    <div class="container">
      <div class="row">
        <div class="center">
          <h1>Generate 2 Files With Same MD5</h1>
          <form method="post" action="<%= request.getContextPath() %>/generateFiles">

            <div class="form-group">
              <label for="scriptLanguage" class="cols-sm-2 control-label">Example select</label>
              <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-duplicate" aria-hidden="true"></i></span>
                <select class="form-control" id="scriptLanguage" name="scriptLanguage">
                  <option value="javascript">JavaScript</option>
                  <option value="php">PHP</option>
                  <option value="python">Python</option>
                  <option value="perl">Perl</option>
                  <option value="bashScript">Bash Script</option>
                  <option value="batScript">Bat Script</option>
                </select>
              </div>
            </div>

            <div class="form-group">
              <label for="fileOne" class="cols-sm-2 control-label">File 1 Name:</label>
              <div class="cols-sm-10">
                <div class="input-group">
                  <span class="input-group-addon"><i class="glyphicon glyphicon-file" aria-hidden="true"></i></span>
                  <input type="text" class="form-control" name="fileOne" id="fileOne"  placeholder="output1"/>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label for="codeOne" class="cols-sm-2 control-label">Code to Run on File 1</label>
              <div class="cols-sm-10">
                <div class="input-group">
                  <span class="input-group-addon"><i class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></i></span>
                  <textarea class="form-control" id="codeOne" rows="10" name="codeOne" required></textarea>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label for="fileTwo" class="cols-sm-2 control-label">File 2 Name:</label>
              <div class="cols-sm-10">
                <div class="input-group">
                  <span class="input-group-addon"><i class="glyphicon glyphicon-file" aria-hidden="true"></i></span>
                  <input type="text" class="form-control" name="fileTwo" id="fileTwo"  placeholder="output2"/>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label for="codeTwo" class="cols-sm-2 control-label">Code to Run on File 2</label>
              <div class="cols-sm-10">
                <div class="input-group">
                  <span class="input-group-addon"><i class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></i></span>
                  <textarea class="form-control" id="codeTwo" rows="10" name="codeTwo" required></textarea>
                </div>
              </div>
            </div>

            <div class="form-group pull-right">
              <button type="submit" class="btn btn-primary">Submit</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>
