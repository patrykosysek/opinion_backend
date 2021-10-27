package pl.polsl.opinion_backend.mappers.user;

import org.mapstruct.Mapper;
import pl.polsl.opinion_backend.configuration.security.jwt.JwtFactory;
import pl.polsl.opinion_backend.dtos.user.PreferenceCreateDTO;
import pl.polsl.opinion_backend.entities.user.Preference;

@Mapper(uses = {JwtFactory.class}, componentModel = "spring")
public interface PreferenceMapper {

    Preference toPreference(PreferenceCreateDTO preferenceCreateDTO);

}
