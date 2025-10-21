package com.cleber.diarioGlicemia.repository;

import com.cleber.diarioGlicemia.controller.response.GlicemiaResponse;
import com.cleber.diarioGlicemia.entity.GlicemiaDiaria;
import com.cleber.diarioGlicemia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GlicemiaRepository extends JpaRepository<GlicemiaDiaria, Long> {

    Optional<GlicemiaDiaria> findByDataAndUser(LocalDate momento, User user);

    List<GlicemiaResponse> findByUser_Id(Long usuario_id);
}
