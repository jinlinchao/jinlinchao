import java.util.*;
/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class test4 {
    public static int[][] number;                        //数字
    public static int[] operator;
    public static int [] precedence_priority;         //优先级
    public static double answer[];
    public static double answer_user[];
    public  static int n=0;
    public  static void main(String arg[]){
        int t=0;
        number=new int[3][2];
        precedence_priority=new int[2];
        operator=new int[4];
        answer_user=new double[2];
        answer=new double[2];
        String input;
        int i,j;
        Scanner in=new Scanner(System.in);
        System.out.print("请输入题数,或输入quit退出：");
        input=in.next();
        while(true){
            j=judge(input);
            if(j==1)return;
            if(j==2){
                System.out.print("输入错误请重新输入：");
                input=in.next();
            }
            if(j==3) break;;
        }
        n=(int)answer_user[0];
        for(i=0;i<n;i++){
            answer_user[0]=1;answer_user[1]=1;answer[0]=1;answer[1]=1;
            number[0][0]=1 + (int) (Math.random() * 10);number[0][1]=1;
            number[1][0]=1 + (int) (Math.random() * 10);number[1][1]=1;
            number[2][0]=1 + (int) (Math.random() * 10);number[2][1]=1;
            operator[0]=(int)(Math.random() * 4);
            operator[1]=(int)(Math.random() * 4);
            precedence_priority[0]=(int)(Math.random() * 2);
            precedence_priority[1]=1-precedence_priority[0];
            output();
            input=in.next();
            while(true)                 //通过函数将字符串分解成分子和分母并存入用户输入答案数组
            {
                j=judge(input);
                if(j==1)return;
                if(j==2){
                    System.out.print("输入错误请重新输入：");
                    input=in.next();
                }
                else break;;
            }
            if((answer_user[0]/answer_user[1])==(answer[0]/answer[1])){
                System.out.println("恭喜你答对了");
                t++;
            }
            else System.out.println("很遗憾，你答错了");
        }
        System.out.println("答对"+t+"道题");
        double k=t*100.0/n;
        System.out.println("正确率："+k+"%");
    }
    public static int judge(String str){
        int length;
        int sum=0;
        int fen_shu_xian=0,xiao_shu_dian=0,fen_shu_num=0,xiao_shu_num=0;
        double xiao_shu;
        int zi_fu=0;
        int i,g=1;

        if(str.equals("quit"))return 1;

        length=str.length();
        char character[]=new char[20];
        for(i=0;i<length;i++)
        {
            character[i]=str.charAt(i);
            if(character[i]=='/') {fen_shu_xian=i;fen_shu_num++;}
            if(character[i]=='.') {xiao_shu_dian=i;xiao_shu_num++;}
            if(((int)character[i]<48||(int)character[i]>57)&&character[i]!='/'&&character[i]!='.') zi_fu=i;
        }
        if(zi_fu!=0||xiao_shu_num>1||fen_shu_num>1)return 2;
        if(xiao_shu_num==0&&fen_shu_num==0){answer_user[0]=Integer.parseInt(str);
        return 3;}
        if(xiao_shu_num==1&&fen_shu_num==0){answer_user[0]=Double.parseDouble(str);return 4;}
        String []remove=str.split("/");
        answer_user[0]=Integer.parseInt(remove[0]);
        answer_user[1]=Integer.parseInt(remove[1]);
        return 0;
    }//判断
    public static String select_operator(int k){
        switch (k){
            case 0:return "＋";
            case 1:return "－";
            case 2:return "×";
            case 3:return "÷";
        }
        return "";
    }
    public static void far(double x1,double y1,double x2,double y2,int k){
        double max;
        if(k==0){                                   //加法运算
            answer[0]=x1*y2+x2*y1;
            answer[1]=y2*y1;
        }
        if(k==1){                                   //减法运算
            answer[0]=x1*y2-x2*y1;
            answer[1]=y2*y1;
        }
        if(k==2){                                   //乘法运算
            answer[0] = x1*x2;
            answer[1] = y1*y2;
        }
        if(k==3){                                   //除法运算
            answer[0] = x1*y2;
            answer[1] = x2*y1;
        }
        if(answer[0]>answer[1])
            max=getmax(answer[0],answer[1]);         //将计算出来的答案进行约分
        else max=getmax(answer[1],answer[0]);
        answer[0]=answer[0]/max;
        answer[1]=answer[1]/max;
    }
    public static int output(){
        if(precedence_priority[0]==1){
            far(number[0][0],number[0][1],number[1][0],number[1][1],operator[0]);
            far(answer[0],answer[1],number[2][0],number[2][1],operator[1]);
            if (operator[0]<2&&operator[1] > 1) {
                System.out.print("(" + number[0][0] + select_operator(operator[0]) + number[1][0] + ")" + select_operator(operator[1]) + number[2][0] + "=");

                return 0;
            }
        }
        else {
            far(number[1][0],number[1][1],number[2][0],number[2][1],operator[1]);
            far(number[0][0],number[0][1],answer[0],answer[1],operator[0]);
            if((operator[1]<2&&operator[0]>0)||(operator[1]>1&&operator[0]==3)){
                System.out.print(number[0][0]+select_operator(operator[0])+"("+number[1][0]+select_operator(operator[1])+number[2][0]+")=");
                return 0;
            }
        }
        System.out.print(number[0][0]+select_operator(operator[0])+number[1][0]+select_operator(operator[1])+number[2][0]+"=");
        return 0;
    }
    private static double getmax(double Max, double Min) {
        double num;
        while(Max%Min!=0) {
            num=Max%Min;
            Max=Min;
            Min=num;
        }
        return Min;
    }
}
