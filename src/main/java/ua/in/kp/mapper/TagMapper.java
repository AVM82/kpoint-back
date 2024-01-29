package ua.in.kp.mapper;

import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.in.kp.config.MapperConfig;
import ua.in.kp.entity.TagEntity;

@Mapper(config = MapperConfig.class)
public interface TagMapper {

    @Mapping(target = "name", source = "tag")
    TagEntity mapStringToTag(String tag);

    Set<TagEntity> mapStringsToTags(Set<String> tags);


    String mapTagToString(TagEntity tag);
    Set<String> mapTagsToStrings(Set<TagEntity> tags);
}
