package servlets;

import core.Constants;
import html.HTML_Templates;

import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(description = "GenerateFiles", urlPatterns = { "/generateFiles" })
public class GenerateFiles extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GenerateFiles() {
        super();
        Constants.loadProperties();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("GET");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String extension = "";

        String scriptLanguage = request.getParameter("scriptLanguage");
        String codeOne = request.getParameter("codeOne");
        String codeTwo = request.getParameter("codeTwo");
        String output1 = "output1";
        String output2 = "output2";

        if(scriptLanguage.equals("javascript")) {
            extension = ".js";
        } else if (scriptLanguage.equals("php")) {
            extension = ".php";
        } else if (scriptLanguage.equals("python")) {
            extension = ".py";
        } else if (scriptLanguage.equals("perl")) {
            extension = ".pl";
        } else if (scriptLanguage.equals("bashScript")) {
            extension = ".sh";
        } else if (scriptLanguage.equals("batScript")) {
            extension = ".bat";
        }

        if(!request.getParameter("fileOne").equals("")) {
            output1 = request.getParameter("fileOne");
        }

        if(!request.getParameter("fileTwo").equals("")) {
            output2 = request.getParameter("fileTwo");
        }

        byte[] md5FileOne = null, md5FileTwo = null;

        String temp = "_" + generateRandomSecureNumber();

        try {
            File templateFile1 = new File(Constants.templatesPath + "template1" + extension);
            File templateFile2 = new File(Constants.templatesPath + "template2" + extension);
            File outFile1 = new File(Constants.outputPath + output1 + temp + extension);
            File outFile2 = new File(Constants.outputPath + output2 + temp + extension);

            if(scriptLanguage.equals("python")) {
                generatePythonFiles(templateFile1, outFile1, codeOne, codeTwo);
                generatePythonFiles(templateFile2, outFile2, codeOne, codeTwo);
            } else if (scriptLanguage.equals("bashScript")) {
                generateBashScriptFiles(templateFile1, outFile1, codeOne, codeTwo);
                generateBashScriptFiles(templateFile2, outFile2, codeOne, codeTwo);
            } else if (scriptLanguage.equals("batScript")) {
                generateBatScriptFiles(templateFile1, outFile1, codeOne, codeTwo);
                generateBatScriptFiles(templateFile2, outFile2, codeOne, codeTwo);
            } else {
                generateScriptFiles(templateFile1, outFile1, codeOne, codeTwo);
                generateScriptFiles(templateFile2, outFile2, codeOne, codeTwo);
            }

            MessageDigest md = MessageDigest.getInstance("MD5");

            md5FileOne = md.digest(Files.readAllBytes(outFile1.toPath()));
            md5FileTwo = md.digest(Files.readAllBytes(outFile2.toPath()));
        } catch (Exception e) {
            out.println(e.getStackTrace());
        }

        String content = HTML_Templates.htmlGeneratedFiles(request.getContextPath(), output1, output2, temp, extension, toHexString(md5FileOne), toHexString(md5FileTwo));

        out.println(HTML_Templates.htmlFile(HTML_Templates.htmlHeader(request.getContextPath(), "Generated Files"), content));
    }

    private void generateScriptFiles(File template, File output, String code1, String code2) throws Exception {
        Files.copy(template.toPath(), output.toPath());
        PrintWriter pW = new PrintWriter(new FileWriter(output,true));

        generateCode(pW, code1);

        pW.append("} else {\n");

        generateCode(pW, code2);

        pW.append("}");

        pW.close();
    }

    private void generatePythonFiles(File template, File output, String code1, String code2) throws Exception {
        Files.copy(template.toPath(), output.toPath());
        PrintWriter pW = new PrintWriter(new FileWriter(output,true));

        generateCode(pW, code1);

        pW.append("else:\n");

        generateCode(pW, code2);

        pW.close();
    }

    private void generateBashScriptFiles(File template, File output, String code1, String code2) throws Exception {
        Files.copy(template.toPath(), output.toPath());
        PrintWriter pW = new PrintWriter(new FileWriter(output,true));

        generateCode(pW, code1);

        pW.append("else\n");

        generateCode(pW, code2);

        pW.append("fi");

        pW.close();
    }

    private void generateBatScriptFiles(File template, File output, String code1, String code2) throws Exception {
        Files.copy(template.toPath(), output.toPath());
        PrintWriter pW = new PrintWriter(new FileWriter(output,true));

        generateCode(pW, code1);

        pW.append(") else (\n");

        generateCode(pW, code2);

        pW.append(")");

        pW.close();
    }

    private static void generateCode(PrintWriter pW, String code) {
        for(String codeLine : code.split("\n")) {
            codeLine = "\t" + codeLine + "\n";
            pW.append(codeLine);
        }
    }

    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++)
        {
            sb.append(String.format("%02X", b[i] & 0xFF));
        }
        return sb.toString();
    }

    private static int generateRandomSecureNumber() {
        try {
            SecureRandom randomSecureRandom = SecureRandom.getInstance("SHA1PRNG");
            return randomSecureRandom.nextInt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
