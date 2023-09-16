package com.meet5.userprofile.dto;

import com.meet5.userprofile.model.ProfileVisits;
import lombok.*;

import java.util.List;


@Data
public class ResponseProfileVisitorList {

   private static List<ProfileVisits> profileVisitsList;

   public static List<ProfileVisits> getProfileVisitsList() {
      return profileVisitsList;
   }
   public static void setProfileVisitsList(List<ProfileVisits> profileVisits) {
     profileVisitsList = profileVisits;
   }
}
