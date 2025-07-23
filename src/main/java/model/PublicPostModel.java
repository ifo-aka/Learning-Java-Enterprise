package model;

import java.util.List;
	
public class PublicPostModel {
   List<PublicPosts>    publicList ;
   public PublicPostModel() {
	   
   }

   public PublicPostModel(List<PublicPosts> publicList) {
	
	this.publicList = publicList;
   }

   public List<PublicPosts> getPublicList() {
	return publicList;
   }

   public void setPublicList(List<PublicPosts> publicList) {
	this.publicList = publicList;
   }	
   
   
}
