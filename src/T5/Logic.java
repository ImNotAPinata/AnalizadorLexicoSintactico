/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 *  ESTE FUE UN PROYECTO ANTERIOR, ESTA ** DEPRECATED **
 * 
 */
package T5;

import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import java
import java.util.Collections;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Owner
 */
public class Logic {

    private String cadena;

    public Logic(String cadena) {
        this.cadena = cadena;
    }

    public Logic() {
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public List<String> ItemToken() {
        StringTokenizer Tokens = new StringTokenizer(getCadena(), "=,+,-,.,*,(,<,>,),/,\t,\n, ,;", true);
        List<String> s = new ArrayList<String>();
        while (Tokens.hasMoreTokens()) {
            s.add(Tokens.nextToken());
        }
        return s;
    }

    public List<String> FlexAnalisis() { // Este es el que mejor esta xD
        int i = 0, x = 0;
        List<String> s = new ArrayList<String>();
        s.addAll(ItemToken());

        String isdigit = "0,1,2,3,4,5,6,7,8,9";
        String digits[] = isdigit.split(",");
        String Condicionales = "(,+,=,-,*,/,!,@,),#,;,$,%,),., ,!,(,-,_,`,~,],/,[,;,',\n,?,>,=,<,,";
        String Condicional[] = Condicionales.split(",");
        String isalpha = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
        String alphan[] = isalpha.split(",");

        List<String> solution = new ArrayList<String>();


        try {
            for (x = 0; x < s.size(); x++) {
                for (i = 0; i < digits.length; i++) {
                    if (s.get(x).contains(digits[i])) {
                       int band=1;
                       if(s.get(x).length()>1)
                       {String ente=s.get(x).substring(1,2);
                       if(ente.contains("0123456789"))
                       {solution.add("315 (Entero)" + "   LEXEMA: " + s.get(x));
                        band = 0;}}
                       if(band!=0)
                       {solution.add("315 (Digito)" + "   LEXEMA: " + s.get(x));}
                        i = digits.length - 1;
                    }
                }
                if (s.get(x).compareTo(".") == 0) {
                    solution.add("310 (Real)" + "   LEXEMA: " + s.get(x - 1) + s.get(x) + s.get(x + 1));
                    i = digits.length - 1;
                }

                for (int y = 0; y < Condicional.length; y++) {
                    if (s.get(x).contains(Condicional[y])) {
                        if (s.get(x).equals("\n")) {
                            solution.add("300 (Caracter Especial)" + "   LEXEMA: Salto de Linea");
                        } else if (s.get(x).equals(" ")) {
                            solution.add("300 (Caracter Especial)" + "   LEXEMA: Espacio en Blanco");
                        } else {
                            solution.add("300 (Caracter Especial)" + "   LEXEMA: " + s.get(x));
                        }
                        y = Condicional.length - 1;
                    }
                }

                for (int z = 0; z < alphan.length; z++) {
                    if (s.get(x).contains(alphan[z])) {
                        solution.add("305 (Letra)" + "   LEXEMA: " + s.get(x));
                        z = alphan.length - 1;
                    }
                }
            }

            if (solution.size() != s.size()) {
                solution.clear();
                solution.add("Error: Valores ingresados Erroneos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return solution;
    }

    public boolean BisonAnalisis() { // Aun falta hacerlo que reconozca mas sentencias

        int i = 0, x = 0;
        boolean validez = false;
        List<String> s = new ArrayList<String>();
        s.addAll(ItemToken());
        //String CaracteresValidoz="<,>,=,==";

        
            for (x = 0; x < s.size(); x++) {
                if (s.get(x).compareTo("if") == 0 && s.get(x + 1) == "(" && s.get(x + 5) == ")" && s.get(x+3).contains("><=")) // reconoce If
                    validez = true;
                }
            
        return validez;
    }
//    public List<String> InterpretaValor()
//    {
//        int i=0;
//        String item = new String();
//        int flag=0;
//        List<String> s= new ArrayList<String>();
//        s.addAll(ItemCadena());
//        List<String> r=new ArrayList<String>();
//        String Sufix = "+,-,*,/, ";
//
//        try {
//            for(i=0;i<s.size();i++)
//            {
//                if(s.get(i).equals(">"))
//                {
//                    flag=0;
//                }
//                if(flag==1)
//                {
//                    item=item.concat(s.get(i));
//                }
//                if(s.get(i).equals("<"))
//                {
//                    flag=1;
//                }
//
//
//            }
//
//            for(i=0;i<item.length();i++)
//            {
//                if(String.valueOf(item.charAt(i)).contentEquals("+"))
//                {
//                    String value1=item.substring(0,i);
//                    String value2=item.substring(i+1,item.length());
//                    String v=String.valueOf(Double.valueOf(value1)+Double.valueOf(value2));
//                    r.add(v);
//                }
//                if(String.valueOf(item.charAt(i)).contentEquals("-"))
//                {
//                    String value1=item.substring(0,i);
//                    String value2=item.substring(i+1,item.length());
//                    String v=String.valueOf(Double.valueOf(value1)-Double.valueOf(value2));
//                    r.add(v);
//                }
//                if(String.valueOf(item.charAt(i)).contentEquals("*"))
//                {
//                    String value1=item.substring(0,i);
//                    String value2=item.substring(i+1,item.length());
//                    String v=String.valueOf(Double.valueOf(value1)*Double.valueOf(value2));
//                    r.add(v);
//                }
//                if(String.valueOf(item.charAt(i)).contentEquals("/"))
//                {
//                    String value1=item.substring(0,i);
//                    String value2=item.substring(i+1,item.length());
//                    String v=String.valueOf(Double.valueOf(value1)/Double.valueOf(value2));
//                    r.add(v);
//                }
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//        return r;
//    }
//
//    public List<String> ListaCaracteresEspeciales ()
//    {
//        int i=0;
//        List<String> s= new ArrayList<String>();
//        s.addAll(ItemCadena());
//        String Condicionales="+,-,*,/,!,@,#,$,%,^,7,*,(,),-,_,`,~,[,],;,',?,<,>";
//        String Condicional[]=Condicionales.split(",");
//        List<String> a = new ArrayList<String>();
//        try {
//        for(i=0;i<s.size();i++)
//        {
//            for(int x=0;x<Condicional.length;x++)
//            if(s.get(i).equals(Condicional[x]))
//            {
//                a.add("300 (Caracter Especial)"+"   LEXEMA="+s.get(i));
//            }
//        }
//        } catch (Exception e) {
//            System.out.println("Error:" + e.getMessage());
//        }
//        return a;
//    }
//
//    public List<String> ListaAlpha()
//    {
//        int i=0;
//        List<String> s= new ArrayList<String>();
//        s.addAll(ItemCadena());
//         String isalpha = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
//         String alphan[]=isalpha.split(",");
//         List<String> alpha = new ArrayList<String>();
//
//         try {
//            for(i=0;i<s.size();i++)
//            {
//                for(int x=0;x<alphan.length;x++)
//                if(s.get(i).equals(alphan[x]))
//                {
//                    alpha.add("305 (Letras)"+"   LEXEMA="+s.get(i));
//                }
//            }
//            } catch (Exception e) {
//                System.out.println("Error:" + e.getMessage());
//            }
//        return alpha;
//
//    }
//
//    public List<String> ListaDigit()
//    {
//        int i=0;
//        List<String> s= new ArrayList<String>();
//        s.addAll(ItemCadena());
//        String isdigit = "0,1,2,3,4,5,6,7,8,9";
//        String digits[]=isdigit.split(",");
//        List<String> digit = new ArrayList<String>();
//
//         try {
//            for(i=0;i<s.size();i++)
//            {
//                for(int x=0;x<digits.length;x++)
//                if(s.get(i).compareTo(digits[x])==0)
//                {
//                   digit.add("310 (Digito)"+"   LEXEMA="+s.get(i));
//                }
//            }
//            } catch (Exception e) {
//                System.out.println("Error:" + e.getMessage());
//            }
//        return digit;
//    }
}
