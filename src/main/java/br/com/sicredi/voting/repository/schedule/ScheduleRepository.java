package br.com.sicredi.voting.repository.schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sicredi.voting.domain.Schedule;
import br.com.sicredi.voting.domain.dto.schedule.response.ScheduleResponse;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    
    @Query(value = "SELECT new br.com.sicredi.voting.domain.dto.schedule.response.ScheduleResponse(s.scheduleId, s.title, s.subject) FROM Schedule s")
    List<ScheduleResponse> findAllSchedules();
}
