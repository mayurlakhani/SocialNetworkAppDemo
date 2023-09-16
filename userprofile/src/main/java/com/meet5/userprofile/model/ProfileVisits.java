package com.meet5.userprofile.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileVisits {
    private Long visitId;
    private Long visitorId;
    private Long visitedProfileId;
    private Date visitTimestamp;

    private int visitCounter = 0;
    public int setVisitCounter(int visitCounter) {
        return this.visitCounter += visitCounter;
    }
}
