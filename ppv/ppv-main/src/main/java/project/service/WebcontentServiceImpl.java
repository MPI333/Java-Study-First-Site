package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.controller.representation.WebcontentRepresentation;
import project.model.Webcontent;
import project.repository.WebcontentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class WebcontentServiceImpl implements WebcontentService {

    private WebcontentRepository webcontentRepository;

    @Autowired
    public WebcontentServiceImpl(WebcontentRepository webcontentRepository) {
        this.webcontentRepository = webcontentRepository;
    }

    @Override
    public List<WebcontentRepresentation> findAll() {
        return webcontentRepository.findAll().stream()
                .map(WebcontentRepresentation::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<WebcontentRepresentation> findById(Long id) {
        return webcontentRepository.findById(id).map(WebcontentRepresentation::new);
    }

    @Override
    public void save(WebcontentRepresentation webcontentRepresentation) {
        Webcontent webcontent = new Webcontent();
        webcontent.setId(webcontentRepresentation.getId());
        webcontent.setIco(webcontentRepresentation.getIco());
        webcontent.setPlatform(webcontentRepresentation.getPlatform());
        webcontent.setTitle(webcontentRepresentation.getTitle());
        webcontent.setAmount(webcontentRepresentation.getAmount());
        webcontent.setLink(webcontentRepresentation.getLink());
        webcontent.setUsers(webcontentRepresentation.getUsers());
        webcontentRepository.save(webcontent);
    }

    @Override
    public void view(WebcontentRepresentation webcontentRepresentation) {
        Webcontent webcontent = new Webcontent();
        webcontent.setId(webcontentRepresentation.getId());
        webcontent.setIco(webcontentRepresentation.getIco());
        webcontent.setPlatform(webcontentRepresentation.getPlatform());
        webcontent.setTitle(webcontentRepresentation.getTitle());
        webcontent.setAmount(webcontentRepresentation.getAmount() - 1);
        webcontent.setLink(webcontentRepresentation.getLink());
        webcontent.setUsers(webcontentRepresentation.getUsers());
        webcontentRepository.save(webcontent);
    }

    @Override
    public void removeWebcontent(Long id) {
        webcontentRepository.deleteById(id);
    }

}
