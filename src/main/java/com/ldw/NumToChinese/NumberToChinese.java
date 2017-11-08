package com.ldw.NumToChinese;

/**
 * Created by Administrator on 2017/7/27.
 */
public class NumberToChinese {


        public static void main(String[] args) {
            NumberToChinese ma = new NumberToChinese();
            Tool to = new Tool();
            ma.initMain();
        }
        public void initMain(){
            testNumberToChinese();
            System.out.println("————————————————————————————————————————");
            testChineseToNumber();
        }
        public void testNumberToChinese(){
            NumberChangeToChinese numToChinese = new NumberChangeToChinese();
            System.out.println("0:"+numToChinese.numberToChinese(0));
            System.out.println("1:"+numToChinese.numberToChinese(1));
            System.out.println("2:"+numToChinese.numberToChinese(2));
            System.out.println("3:"+numToChinese.numberToChinese(3));
            System.out.println("4:"+numToChinese.numberToChinese(4));
            System.out.println("5:"+numToChinese.numberToChinese(5));
            System.out.println("6:"+numToChinese.numberToChinese(6));
            System.out.println("7:"+numToChinese.numberToChinese(7));
            System.out.println("8:"+numToChinese.numberToChinese(8));
            System.out.println("9:"+numToChinese.numberToChinese(9));
            System.out.println("10:"+numToChinese.numberToChinese(10));
            System.out.println("11:"+numToChinese.numberToChinese(11));
            System.out.println("110:"+numToChinese.numberToChinese(110));
            System.out.println("111:"+numToChinese.numberToChinese(111));
            System.out.println("100:"+numToChinese.numberToChinese(100));
            System.out.println("102:"+numToChinese.numberToChinese(102));
            System.out.println("1020:"+numToChinese.numberToChinese(1020));
            System.out.println("1001:"+numToChinese.numberToChinese(1001));
            System.out.println("1015:"+numToChinese.numberToChinese(1015));
            System.out.println("1000:"+numToChinese.numberToChinese(1000));
            System.out.println("10000:"+numToChinese.numberToChinese(100101));
            System.out.println("20010"+numToChinese.numberToChinese(20010));
            System.out.println("20001"+numToChinese.numberToChinese(20001));
            System.out.println("100000:"+numToChinese.numberToChinese(100000));
            System.out.println("1000000:"+numToChinese.numberToChinese(1000000));
            System.out.println("10000000"+numToChinese.numberToChinese(10000000));
            System.out.println("100000000:"+numToChinese.numberToChinese(100000000));
            System.out.println("1000000000"+numToChinese.numberToChinese(1000000000));
            System.out.println("2000105"+numToChinese.numberToChinese(2000105));
            System.out.println("20001007:"+numToChinese.numberToChinese(20001007));
            System.out.println("2005010010:"+numToChinese.numberToChinese(2005010010));
        }
        public void testChineseToNumber(){
            ChineseChangeToNumber chineseToNumber = new ChineseChangeToNumber();
            System.out.println("二十亿零五千五百零一万四千零一十:"+chineseToNumber.ChineseToNumber("二十亿零五千五百零一万四千零一十"));
            System.out.println("二千万一千零七:"+chineseToNumber.ChineseToNumber("二千万一千零七"));
            System.out.println("二万零一:"+chineseToNumber.ChineseToNumber("二万零一"));
            System.out.println("二万零一十:"+chineseToNumber.ChineseToNumber("二万零一十"));
            System.out.println("一万:"+chineseToNumber.ChineseToNumber("一万"));
            System.out.println("一千零一十五:"+chineseToNumber.ChineseToNumber("一千零一十五"));
            System.out.println("一千:"+chineseToNumber.ChineseToNumber("一千"));
            System.out.println("一亿:"+chineseToNumber.ChineseToNumber("一亿"));

        }

}
