import java.io.IOException;

public class Main {
    public static void main(String args[])throws IOException{
        GUI gui = new GUI();
        Search s = new Search();
            String item;
            String re;
        String code="";
        gui.setWindow();
            do {
                item = gui.GetInput("Enter the subject you are trying to find\n Or type -1 to exit");
                if(item.equals("-1")){
                    break;}
                re = s.findPage(item);
                if(re.equals("-1")){
                    System.out.println("Could not find page");
                    }
                else{
                    String[] li = re.split(",");
                    int i = Integer.parseInt(li[0].trim());
                    int j = Integer.parseInt(li[1].trim());
                    code = gui.GetInput("enter a course code");
                    String y = s.getCourse(s.getPage(i,j),code);
                    if(y==null){
                        y="Cannot find subject with code " + code;
                    }
                    gui.add(y+System.getProperty("line.separator"));
                    //System.out.println(s.getPage(i,j));
                }
            }while(true);
    }
}
