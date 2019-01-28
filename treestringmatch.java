//public class treestringmatch {
//
//
//
//
//
//    public boolean isMatch(Node regex, String s){
//        if(node.type == CHAR){
//            return (s.length() == 0 && s.charAt(0) == regex.c);
//        }
//        if(node.type == DIST){
//            return isMatch(regex.left, s) || isMatch(regex.right, s);
//        }
//        if(node.type == CONCAT){
//            for(int i =0; i < s.length(); i++){
//                if(isMatch(node.left, s.substring(0,i)) && isMatch(node.right, s.substring(i))){
//                    return true;
//                }
//            }
//        }
//        if(node.type == REP){
//            for(int i = 0; i < s.length(); i++){
//                if(isMatch(node.left, s.substring(0, i))){
//                    if(isMatch(node.right), s.substring(i)){
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//}
