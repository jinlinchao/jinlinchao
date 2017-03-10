/**
 * Created by Administrator on 2017/3/10 0010.
 */
import  java.util.*;

public class rw2 {
    public static int answer[][];
    public static int answer_user[][];
    public static int t;

    public static void main(String args[]){
        int numberone[][]=new int[100][2];
        int numbertwo[][]=new int[100][2];
        answer=new int[100][2];
        answer_user=new int[100][2];
        int symbol[]=new int[100];
        int number,i,max,j;
        t=0;

        System.out.print("请输入题数：");
        Scanner in=new Scanner(System.in);
        number=in.nextInt();

        for(i=0;i<number;i++) {
            numberone[i][1] = 1 + (int) (Math.random() * 100);
            numberone[i][0] = 1 + (int) (Math.random() * numberone[i][1]);
            max = getmax(numberone[i][0], numberone[i][1]);
            numberone[i][0] = numberone[i][0] / max;
            numberone[i][1] = numberone[i][1] / max;

            numbertwo[i][1] = 1 + (int) (Math.random() * 100);
            numbertwo[i][0] = 1 + (int) (Math.random() * numbertwo[i][1]);
            max = getmax(numbertwo[i][0], numbertwo[i][1]);
            numbertwo[i][0] = numbertwo[i][0] / max;
            numbertwo[i][1] = numbertwo[i][1] / max;

            symbol[i] = (int) (Math.random() * 4);

        }
        for(i=0;i<number;i++){
            if(symbol[i]==0){                                   //加法运算
                answer[i][0]=numberone[i][0]*numbertwo[i][1]+numberone[i][1]*numbertwo[i][0];
                answer[i][1]=numberone[i][1]*numbertwo[i][1];
            }
            if(symbol[i]==1){                                   //减法运算
                answer[i][0] = numberone[i][0]*numbertwo[i][1]-numberone[i][1]*numbertwo[i][0];
                answer[i][1] = numberone[i][1]*numbertwo[i][1];
            }
            if(symbol[i]==2){                                   //乘法运算
                answer[i][0] = numberone[i][0]*numbertwo[i][0];
                answer[i][1] = numberone[i][1]*numbertwo[i][1];
            }
            if(symbol[i]==3){                                   //除法运算
                answer[i][0] = numberone[i][0]*numbertwo[i][1];
                answer[i][1] = numberone[i][1]*numbertwo[i][0];
            }
            if(answer_user[i][0]>answer_user[i][1])
                max=getmax(answer[i][0],answer[i][1]);         //将计算出来的答案进行约分
            else max=getmax(answer[i][1],answer[i][0]);
            answer[i][0]=answer[i][0]/max;
            answer[i][1]=answer[i][1]/max;
        }

        for(i=0;i<number;i++) {                                 //打印算式
            if(numberone[i][0]%numberone[i][1]!=0)                //判断是否是整数
                System.out.print("("+numberone[i][0]+"/"+numberone[i][1]+")");
            else
                System.out.print(numberone[i][0]/numberone[i][1]);

            if(symbol[i]==0)
                System.out.print("+");
            else if(symbol[i]==1)
                System.out.print("-");
            else if(symbol[i]==2)
                System.out.print("*");
            else if(symbol[i]==3)
                System.out.print("÷");

            if(numbertwo[i][0]%numbertwo[i][1]!=0)              //判断是否是整数
                System.out.print("("+numbertwo[i][0]+"/"+numbertwo[i][1]+")=");
            else
                System.out.print(numbertwo[i][0]/numbertwo[i][1]+"=");

            String str=in.next();       //获取用户输入的答案字符串

            while(input(str,i)==1)                 //通过函数将字符串分解成分子和分母并存入用户输入答案数组
            {
                str=in.next();
            }
        }
        System.out.println("答对"+t+"道题");
        float k=(float)t/number*100;
        System.out.println("正确率："+k+"%");
    }

    private static int input(String str,int n)
    {
        int length;
        int sum=0;
        int fen_shu_xian=0;
        int xiao_shu_dian=0;
        double xiao_shu;
        int zi_fu=0;
        int i;
        int g=1;

        length=str.length();
        char character[]=new char[20];
        for(i=0;i<length;i++)
        {
            character[i]=str.charAt(i);
            if(character[i]=='/') fen_shu_xian=i;
            if(character[i]=='.') xiao_shu_dian=i;
            if(((int)character[i]<48||(int)character[i]>57)&&character[i]!='/'&&character[i]!='.') zi_fu=i;
        }

        if(zi_fu!=0||answer_user[n][1]==0){
            System.out.println("你输入错误，请重新输入");
            return 1;
        }
        else if(fen_shu_xian!=0 && xiao_shu_dian==0&&zi_fu==0){             //分数
            if(character[0]=='-') {
                sum=0;
                for(i=1;i<fen_shu_xian;i++){
                    sum=sum*10+(int)character[i]-48;
                }
                sum*=-1;
            }
            else{
                sum=0;
                for(i=0;i<fen_shu_xian;i++){
                    sum=sum*10+(int)character[i]-48;
                }
            }
            answer_user[n][0]=sum;

            sum=0;
            for(i=fen_shu_xian+1;i<length;i++){
                sum=sum*10+(int)character[i]-48;
            }
            answer_user[n][1]=sum;

            if((double)(answer_user[n][0]/answer_user[n][1])==(double)(answer[n][0]/answer[n][1])){
                System.out.println("恭喜你答对了");
                t++;
            }
            else System.out.println("很遗憾，你答错了");
        }

        else if(fen_shu_xian==0 && xiao_shu_dian==0&&zi_fu==0){                   //整数
            if(character[0]=='-'){
                sum=0;
                for(i=1;i<length;i++){
                    sum=sum*10+(int)character[i]-48;
                }
                sum*=-1;
            }
            else {
                sum=0;
                for(i=0;i<length;i++) sum=sum*10+(int)character[i]-48;
            }
            if(sum==(answer[n][0]/answer[n][1])){
                System.out.println("恭喜你答对了");
                t++;
            }
            else System.out.println("很遗憾，你答错了");
        }

        else if(fen_shu_xian==0&&xiao_shu_dian!=0&&zi_fu==0){             //小数
            xiao_shu=Double.parseDouble(str);
            if(xiao_shu-(double)(answer[n][0]/answer[n][1])<0.00000001){
                System.out.println("恭喜你答对了");
                t++;
            }
            else System.out.println("很遗憾，你答错了");
        }

        return 0;
    }

    private static int getmax(int Max, int Min) {
        int num;
        while(Max%Min!=0) {
            num=Max%Min;
            Max=Min;
            Min=num;
        }
        return Min;
    }
}
