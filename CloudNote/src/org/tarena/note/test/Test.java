package org.tarena.note.test;

public class Test {
public static void main(String[] args) {
	
	/*
	 * 
	 * 第一步：寻找右小括号41 右中括号93  右大括号125
	 * 第二步：找到一个之后，统计它左侧对应的左右括号数leftnum/rightnum，
	 *           判断条件40 ,91,123，如果left==0返回false,
	 * 第三步：如果存在左括号数等于包含自身在内的右括号数，并且（右下标-左下标）%2=1时候符合匹配，否则返回false
	 * 
	 */
         String s = "{ }";
      
         if(right(s)&left(s)&isthat(s)){
        	 System.out.println("result="+true);
         }else{
        	 System.out.println("result="+false);
         }
}
public static boolean  isthat(String s){
	boolean isthat = false;
	char[] a = s.toCharArray();
	for(int i=0;i<a.length;i++){
		if(isleft(a[i])||isright(a[i])||a[i]==32){
			isthat = true;
		}else{
			isthat = false;
		}
		
	}
	
	return isthat;
}

public static boolean left(String s){
	int leftnum =0;
	int rightnum=0;
     boolean yy = true;
     char[] as = s.toCharArray();
     for(int i=0;i<as.length;i++){
    	 char a = as[i];
    	 if(isleft(a)){// 第一步：寻找右小括号41 右中括号93  右大括号125
    		 
    		 leftnum =0;
    		 rightnum=0;
    		 for(int j=i;j<as.length;j++){
    		//第二步：找到一个之后，统计它左侧对应的左右括号数leftnum/rightnum，
    	    //判断条件40 ,91,123，如果left==0返回false,
    		 	 if(as[j]==as[i]){
    		 		 leftnum +=1;
    		 	 }
    		 	 if(as[j]==as[i]+1||as[j]==as[i]+2){
    		 		rightnum +=1; 
    		 	 }
    			 if(rightnum ==leftnum){
    				 //第三步：如果存在左括号数等于包含自身在内的右括号数，并且（右下标-左下标）%2=1时候符合匹配，否则返回false
    				 if((j-i)%2==1){
//    					 System.out.println("i="+i+":: j="+j);
    					 yy =true;
    					 break;
    				 }else {
    					 yy =false;
    				 }
    			 }else {
					 yy =false;
				 }
    		 }
    	 }
    	 if(yy==false){
    		 break;
    	 }
     }
     System.out.println("left="+yy);
	return yy;
	
}

public static boolean right(String s){
 	int leftnum =0;
	int rightnum=0;
     boolean yy = true;
     char[] as = s.toCharArray();
     for(int i=0;i<as.length;i++){
    	 char a = as[i];
    	 if(isright(a)){// 第一步：寻找右小括号41 右中括号93  右大括号125
    		 leftnum =0;
    		 rightnum=0;
    		 for(int j=i;j>-1;j--){
    		//第二步：找到一个之后，统计它左侧对应的左右括号数leftnum/rightnum，
    	    //判断条件40 ,91,123，如果left==0返回false,
    		 	 if(as[j]==as[i]){
    		 		 leftnum +=1;
    		 	 }
    		 	 if(as[j]==as[i]-1||as[j]==as[i]-2){
    		 		 
    		 		rightnum +=1; 
    		 	 }
    			 if(rightnum ==leftnum){
    				
    				 //第三步：如果存在左括号数等于包含自身在内的右括号数，并且（右下标-左下标）%2=1时候符合匹配，否则返回false
    				 if((i-j)%2==1){
    					 
    					 yy =true;
    					 break;
    				 }else {
    					 yy =false;
    					 
    				 }
    			 }else {
					 yy =false;
				 }
    			 
    		 }
    	 }
     if(yy==false){
		 break;
	 }
     }
     System.out.println("right="+yy);
	return yy;
}


public static boolean isright(char a){
	int b = a;
	boolean boo = false;
	if(b==41||b==93||b==125){
	boo=true;
		
	}else{
		boo=false;
	}
	return boo;
}

public static boolean isleft(char a){
	int b = a;
	boolean boo = false;
	if(b==40||b==91||b==123){
	boo=true;
		
	}else{
		boo=false;
	}
	return boo;
}
}
