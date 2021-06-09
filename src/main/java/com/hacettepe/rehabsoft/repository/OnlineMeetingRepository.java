package com.hacettepe.rehabsoft.repository;

import com.hacettepe.rehabsoft.entity.OnlineMeeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OnlineMeetingRepository extends JpaRepository<OnlineMeeting,Long> {

    @Query("select o from OnlineMeeting o where o.doctorUser.username=?1 or o.patientUser.username=?1 order by o.meetingDate asc")
    List<OnlineMeeting> getByUsername(String username);

}
