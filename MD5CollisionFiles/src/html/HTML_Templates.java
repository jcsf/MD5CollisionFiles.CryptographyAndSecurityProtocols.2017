package html;

public class HTML_Templates {

    public static String htmlFile(String header, String content) {
        return  "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>" +
                header +
                "<body onload=\"addCodeKeyEvents();\">" +
                content +
                "</body>\n" +
                "</html>";
    }

    public static String htmlHeader (String requestPath, String title) {
        return  "  <head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "    <link href=\"" + requestPath + "/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                "    <link href=\"" + requestPath + "/css/bootstrap-theme.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>\n" +
                "    <script src=\"" + requestPath + "/js/bootstrap.min.js\"></script>\n" +
                "    <script src=\"" + requestPath + "/js/functions.js\"></script>\n" +
                "    <title>" + title + "</title>\n" +
                "  </head>";
    }

    public static String htmlGeneratedFiles(String requestPath, String fileOne, String fileTwo, String ref, String extension, String md5One, String md5Two, String sha256One, String sha256Two) {
        String downloadURI_1 = String.format("%s/download?file=%s&ref=%s&ext=%s", requestPath, fileOne, ref, extension);
        String downloadURI_2 = String.format("%s/download?file=%s&ref=%s&ext=%s", requestPath, fileTwo, ref, extension);

        return "<div class=\"container\">\n" +
                "   <div class=\"jumbotron\">\n" +
                "       <h1>Generated Files With Same MD5</h1>\n" +
                "       <hr class=\"my-4\">\n" +
                "       <div class=\"row\">\n" +
                "           <div class=\"col-sm-6 text-center\">\n" +
                "               <p><i class=\"glyphicon glyphicon-file\" aria-hidden=\"true\" style=\"font-size: 75px;\"></i></p>\n" +
                "               <h2>" + fileOne + extension + "</h2>\n" +
                "               <p><b>MD5</b><br>" + md5One + "</p>\n" +
                "               <p><b>SHA256</b><br><span style=\"font-size: 12.5px;\">" + sha256One + "</span></p>\n" +
                "               <a class=\"btn btn-success btn-lg btn-block\" href=\""+ downloadURI_1 +"\" role=\"button\">Download File</a>\n" +
                "           </div>\n" +
                "           <div class=\"col-sm-6 text-center\">\n" +
                "               <p><i class=\"glyphicon glyphicon-file\" aria-hidden=\"true\" style=\"font-size: 75px;\"></i></p>\n" +
                "               <h2>" + fileTwo + extension + "</h2>\n" +
                "               <p><b>MD5</b><br>" + md5Two + "</p>\n" +
                "               <p><b>SHA256</b><br><span style=\"font-size: 12.5px;\">" + sha256Two + "</span></p>\n" +
                "               <a class=\"btn btn-danger btn-lg btn-block\" href=\""+ downloadURI_2 +"\" role=\"button\">Download File</a>\n" +
                "           </div>\n" +
                "       </div>\n" +
                "       <hr class=\"my-4\">\n" +
                "       <div class=\"row\">\n" +
                "           <div class=\"col-sm-12 text-center\">\n" +
                "               <a class=\"btn btn-primary btn-lg\" href=\""+ requestPath +"\" role=\"button\">Back To Generate Files</a>\n" +
                "           </div>\n" +
                "       </div>\n" +
                "   </div>\n" +
                "</div>";
    }

}
