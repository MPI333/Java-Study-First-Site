package project.service;

import project.controller.representation.WebcontentRepresentation;

import java.util.List;
import java.util.Optional;

public interface WebcontentService {

    List<WebcontentRepresentation> findAll();

    Optional<WebcontentRepresentation> findById(Long id);

    void save(WebcontentRepresentation webcontentRepresentation);

    void removeWebcontent(Long id);

}
