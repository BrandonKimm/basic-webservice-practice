package com.brandon.book.springboot.web;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

public class ProfileControllerUnitTest {

    @Test
    public void real_profile이_조회된다(){
        //given
        String expectedProfile = "real";
        MockEnvironment environment = new MockEnvironment();
        environment.addActiveProfile(expectedProfile);
        environment.addActiveProfile("oauth");
        environment.addActiveProfile("real-db");

        ProfileController profileController = new ProfileController(environment);

        //when
        String profile = profileController.profile();

        //then
        Assertions.assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void real_profile이_없으면_첫번째가_조회된다(){
        //given
        String expectedProfile = "default";
        MockEnvironment environment = new MockEnvironment();
        environment.addActiveProfile(expectedProfile);

        ProfileController profileController = new ProfileController(environment);

        //when
        String profile = profileController.profile();

        //then
        Assertions.assertThat(profile).isEqualTo(expectedProfile);
    }
}
