import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;

public class Search {
    PDFParser parser = null;
    PDDocument pdDoc = null;
    COSDocument cosDoc = null;
    PDFTextStripper pdfStripper = null;

    public Search() {
        try {
            parser = new PDFParser(new RandomAccessFile(new File("C:\\Users\\chris\\Downloads\\2017-18_FAS_Calendar_Sept13.pdf"), "r"));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            pdDoc.getNumberOfPages();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cosDoc != null) {
                    cosDoc.close();
                }
                if (pdDoc != null) {
                    pdDoc.close();
                }
            } catch (Exception e1) {
                e.printStackTrace();
            }
        }
    }

    public String findPage(String input) {
        try {
            pdfStripper.setStartPage(2);
            pdfStripper.setEndPage(3);
            String[] m = pdfStripper.getText(pdDoc).split("\n");
            String no = "";
            String n1 = "";
            String head = "";
            int l = 0;
            for (int i = 0; i < m.length; i++) {
                if (m[i].toLowerCase().contains(input.toLowerCase())) {
                    int subject = m[i].indexOf(".");
                    head = m[i].substring(0, subject);
                    int ind = m[i].lastIndexOf('.') + 1;
                    no = m[i].substring(ind, m[i].length());
                    i++;
                    ind = m[i].lastIndexOf('.') + 1;
                    n1 = m[i].substring(ind, m[i].length());
                    l++;
                    break;
                }
            }
            if (l == 0) {
                return "-1";
            }
            String result = no + "," + n1 + "," + head;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cosDoc != null) {
                    cosDoc.close();
                }
                if (pdDoc != null) {
                    pdDoc.close();
                }
            } catch (Exception e1) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String getPage(int s_page, int e_page) throws IOException {
        pdfStripper.setStartPage(s_page);
        pdfStripper.setEndPage(e_page);
        return pdfStripper.getText(pdDoc);
    }

    public String getCourse(String page, String subject) {
        String [] p = page.split(System.getProperty("line.separator"));
        int i=1;
        int ind =0,ind2=0;
        boolean next = false;
        while(i<p.length){
            //System.out.println(p[i]);
            if((p[i].contains(subject))&&(p[i+2].contains("Hours")|p[i+1].contains("Hours"))){
                ind =i;
                next = true;
            }
            if(next){
                if(p[i].length()==1){
                ind2=i;
                break;
                }
            }
            i++;
        }
        if(ind==0){
            return null;
        }
        String result="";
        for(int m = ind;m<ind2;m++){
            result = result + p[m] + System.getProperty("line.separator");
        }
        return result;
    }
    }
