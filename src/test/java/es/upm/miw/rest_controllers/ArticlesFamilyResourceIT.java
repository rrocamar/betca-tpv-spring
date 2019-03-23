package es.upm.miw.rest_controllers;

import es.upm.miw.documents.FamilyComposite;
import es.upm.miw.documents.FamilyType;
import es.upm.miw.dtos.ArticleFamilyDto;
import es.upm.miw.dtos.ArticleFamilyMinimumDto;
import es.upm.miw.dtos.ArticleMinimumDto;
import es.upm.miw.repositories.FamilyCompositeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ApiTestConfig
public class ArticlesFamilyResourceIT {

    @Autowired
    private RestService restService;

    @Autowired
    private FamilyCompositeRepository familyCompositeRepository;

    @Test
    void testCreateFamilyArticle() {
        assertNotNull(familyCompositeRepository.findByDescription("test"));
        ArticleMinimumDto response = this.restService.loginOperator().restBuilder(new RestBuilder<ArticleMinimumDto>())
                .clazz(ArticleMinimumDto.class).path(ArticlesFamilyResource.ARTICLES_FAMILY + ArticlesFamilyResource.ARTICLE)
                .param("description", "test").body(new ArticleMinimumDto("8400000000017", "Zarzuela - Falda T2"))
                .post().build();
        assertEquals("Zarzuela - Falda T2", response.getDescription());
        FamilyComposite familyComposite = familyCompositeRepository.findByDescription("test");
        familyComposite.getFamilyCompositeList().clear();
        familyCompositeRepository.save(familyComposite);
    }

    @Test
    void testCreateFamilyComposite() {
        assertNull(familyCompositeRepository.findByDescription("create"));
        assertNotNull(familyCompositeRepository.findByDescription("test"));
        ArticleFamilyDto response = this.restService.loginOperator().restBuilder(new RestBuilder<ArticleFamilyDto>())
                .clazz(ArticleFamilyDto.class).path(ArticlesFamilyResource.ARTICLES_FAMILY + ArticlesFamilyResource.COMPOSITE)
                .param("description", "test").body(new ArticleFamilyDto(FamilyType.ARTICLES, "C", "create"))
                .post().build();
        assertEquals("create", response.getDescription());
        assertNotNull(familyCompositeRepository.findByDescription("create"));
        familyCompositeRepository.delete(familyCompositeRepository.findByDescription("create"));
    }

    @Test
    void testDeleteFamilyCompositeItem() {
        assertNotNull(familyCompositeRepository.findByDescription("test"));
        this.restService.loginOperator()
                .restBuilder(new RestBuilder<ArticleFamilyMinimumDto>()).clazz(ArticleFamilyMinimumDto.class)
                .path(ArticlesFamilyResource.ARTICLES_FAMILY)
                .param("description", "test").delete().build();
        assertNull(familyCompositeRepository.findByDescription("test"));
        familyCompositeRepository.save(new FamilyComposite(FamilyType.ARTICLES, "T", "test"));
    }

    @Test
    void testReadAllComponentsInAFamily (){
        List<ArticleFamilyDto> dtos = Arrays.asList(this.restService.loginOperator()
                .restBuilder(new RestBuilder<ArticleFamilyDto[]>()).clazz(ArticleFamilyDto[].class)
                .path(ArticlesFamilyResource.ARTICLES_FAMILY)
                .path(ArticlesFamilyResource.DESCRIPTION).expand("root").get().build());
        assertNotNull(dtos);
    }

    @Test
    void testReadAllFamilyCompositeByFamilyType() {
        List<ArticleFamilyMinimumDto> articleFamilyMinimumDtoList = Arrays.asList(this.restService.loginOperator()
                .restBuilder(new RestBuilder<ArticleFamilyMinimumDto[]>()).clazz(ArticleFamilyMinimumDto[].class)
                .path(ArticlesFamilyResource.ARTICLES_FAMILY)
                .param("familyType", "ARTICLES").get().build());
        assertTrue(articleFamilyMinimumDtoList.size() > 1);
    }
}
