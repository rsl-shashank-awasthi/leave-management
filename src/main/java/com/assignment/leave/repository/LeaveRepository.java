package com.assignment.leave.repository;

import com.assignment.leave.model.LeaveRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LeaveRepository {

    private final Map<Long, LeaveRequest> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public LeaveRequest save(LeaveRequest leaveRequest) {
        if (leaveRequest.getId() == null) {
            leaveRequest.setId(idGenerator.getAndIncrement());
        }
        store.put(leaveRequest.getId(), leaveRequest);
        return leaveRequest;
    }

    public List<LeaveRequest> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<LeaveRequest> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }
}