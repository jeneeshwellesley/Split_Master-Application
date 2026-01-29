package com.jeneesh.splitmaster.Split.Master.repositories;

import com.jeneesh.splitmaster.Split.Master.entities.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Groups,Long> {

}
