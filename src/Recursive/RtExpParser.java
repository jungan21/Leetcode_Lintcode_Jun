//package Recursive;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Stack;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
//public class RtExpParser {
//
//
//  private RtExpParser() {
//  }
//  
//  private final static Pattern RT_EXP = Pattern.compile("[\\$#%]\\{([^}]+)}");
//
//  static public Token convert2Token(String exp) {
//
//    Matcher m = RT_EXP.matcher(exp);
//    Position anch = new Position(0);
//    Stack<String> pendingTxts = new Stack<String>();
//    pendingTxts.push("");
//    return prepareToken(m, exp, anch, pendingTxts);
//  }
//
//  static private Token prepareToken(Matcher m, String exp, Position anch,
//      Stack<String> pendingTxts) {
//    String refTxt = pendingTxts.peek();
//    List<Token> list = new ArrayList<Token>();
//    int pendingTxtsCount = pendingTxts.size();
//    while (m.find(anch.getAnch())) {
//      int start = m.start();
//      // handle the skipped characters
//      if (start != anch.getAnch()) {
//        list.add(new CharacterToken(exp.substring(anch.getAnch(), start)));
//      }
//      // set position
//      anch.setAnch(m.end());
//      String group = m.group(1);
//      // handler the matched runtime expression
//      if (exp.charAt(start) == '$') {
//        list.add(new ExpressionToken(group));
//      } else if (exp.charAt(start) == '#') {
//        list.add(new MessageToken(group));
//      } else if (exp.charAt(start) == '%') {
//        String txt = group;
//        if (txt.charAt(0) == '/') {
//          // it is end tag
//          txt = txt.substring(1);
//          if (refTxt.equals(txt)) {
//            // correct stack
//            return new EnableToken(txt, list);
//          } else {
//            // incorrect stack  
//            logger.error("find unmatched end-enabled-token: "
//                + txt
//                + (pendingTxtsCount == 1 ? ", there is no begin-enabled-token."
//                    : ", the expected end-enabled-token is: " + refTxt));
//            if (pendingTxts.contains(txt)) {
//              // if the end tag is in stack, break, and reset anch
//              anch.setAnch(start);
//              return new EnableToken(refTxt, list);
//            } else {
//              // otherwise, ignore it (type error)
//              ;
//            }
//          }
//        } else {
//          pendingTxts.push(txt);
//          list.add(prepareToken(m, exp, anch, pendingTxts));
//          pendingTxts.pop();
//        }
//      }
//    }
//
//    // handle the tailing characters
//    if (anch.getAnch() < exp.length()) {
//      list.add(new CharacterToken(exp.substring(anch.getAnch(), exp.length())));
//      anch.setAnch(exp.length());
//    }
//    if (pendingTxtsCount != 1) {
//      logger
//          .error("no matched end-enabled-token at the end of line: " + refTxt);
//    }
//    return new EnableToken(list);
//  }
//
//  private static class Position {
//    private int anch;
//
//    public Position(int anch) {
//      this.anch = anch;
//    }
//
//    public int getAnch() {
//      return anch;
//    }
//
//    public void setAnch(int anch) {
//      this.anch = anch;
//    }
//
//  }
//
//}
