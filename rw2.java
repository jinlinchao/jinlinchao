import java.util.Random;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/2/14 0014.
 */
public class rw2 {
    public static void main(String[] args) {
        int num1,num2,num3,num4,max,Fm,Fz;
        int k,j;
        int T=0;
        int n=0;
        String answer="0";
        Random random=new Random();
        while(n<10)
        {
            k=random.nextInt();
            num1=Math.abs(k%10)+1;
            num3=(int)(Math.random()*(num1-1))+1;
            k=random.nextInt();
            num2=Math.abs(k%10)+1;
            num4=(int)(Math.random()*(num2-1))+1;

            k=random.nextInt();
            j=Math.abs(k%7);
            if(0==j)
            {
                System.out.println("("+num3+"/"+num1+")+("+num4+"/"+num2+")=");
                Fm=num1*num2;
                Fz=num1*num4+num2*num3;
                if(0==Fz%Fm)
                {
                    Integer s=Fz/Fm;
                    answer=s.toString();
                }
                else {
                    if(Fm>Fz) max=getmax(Fm,Fz);
                    else max=getmax(Fz,Fm);
                    Fm/=max;
                    Fz/=max;
                    answer=Fm+"/"+Fz;
                }
            }
            else if(1==j)
            {
                System.out.println("("+num3+"/"+num1+")-("+num4+"/"+num2+")=");
                Fm=num1*num2;
                Fz=num2*num3-num1*num4;
                if(0==Fz%Fm)
                {
                    Integer s=Fz/Fm;
                    answer=s.toString();
                }
                else {
                    if(Fm>Fz) max=getmax(Fm,Fz);
                    else max=getmax(Fz,Fm);
                    Fm/=max;
                    Fz/=max;
                    answer=Fm+"/"+Fz;
                }
            }
            else if(2==j)
            {
                System.out.println("("+num3+"/"+num1+")*("+num4+"/"+num2+")=");
                Fm=num1*num2;
                Fz=num3*num4;
                if(0==Fz%Fm)
                {
                    Integer s=Fz/Fm;
                    answer=s.toString();
                }
                else {
                    if(Fm>Fz) max=getmax(Fm,Fz);
                    else max=getmax(Fz,Fm);
                    Fm/=max;
                    Fz/=max;
                    answer=Fm+"/"+Fz;
                }
            }
            else if(3==j)
            {
                System.out.println("("+num3+"/"+num1+")/("+num4+"/"+num2+")=");
                Fm=num1*num4;
                Fz=num2*num3;
                if(0==Fz%Fm)
                {
                    Integer s=Fz/Fm;
                    answer=s.toString();
                }
                else {
                    if(Fm>Fz) max=getmax(Fm,Fz);
                    else max=getmax(Fz,Fm);
                    Fm/=max;
                    Fz/=max;
                    answer=Fm+"/"+Fz;
                }
            }
            else if(4==j)
            {
                System.out.println(num1+"+"+num2+"=");
                Integer s=num1+num2;
                answer=s.toString();
            }
            else if(5==j)
            {
                System.out.println(num1+"-"+num2+"=");
                Integer s=num1-num2;
                answer=s.toString();
            }
            else if(6==j)
            {
                System.out.println(num1+"*"+num2+"=");
                Integer s=num1*num2;
                answer=s.toString();
            }
            else if(7==j)
            {
                System.out.println(num1+"/"+num2+"=");
                if(num1%num2==0)
                {
                    Integer s=num1/num2;
                    answer=s.toString();
                }
                else{
                    if(num1>num2) max=getmax(num1,num2);
                    else max=getmax(num2,num1);
                    num1/=max;
                    num2/=max;
                    answer=num1+"/"+num2;
                }
            }
            Scanner input=new Scanner(System.in);
            String reanswer=input.next();
            if(reanswer.equals(answer))  T++;
            n++;
        }
        System.out.println("答对"+T+"道题");
        float i=(float)T/n*100;
        System.out.println("正确率："+i+"%");
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
