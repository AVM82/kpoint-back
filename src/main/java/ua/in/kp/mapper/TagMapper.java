package ua.in.kp.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ua.in.kp.config.MapperConfig;
import ua.in.kp.entity.TagEntity;

@Mapper(config = MapperConfig.class)
public interface TagMapper {

    TagEntity mapStringToTag(String tag);

    String mapTagToString(TagEntity tag);

    @Named("mapTagToStringSet")
    default Set<String> mapTagToStringSet(Set<TagEntity> tags) {
        return tags.stream()
                .map(TagEntity::getName)
                .collect(Collectors.toSet());
    }

    @Named("mapStringToTagSet")
    default Set<TagEntity> mapStringToTagSet(Set<String> tags) {
        return tags.stream()
                .map(tagName -> {
                    TagEntity tag = new TagEntity();
                    tag.setName(tagName);
                    return tag;
                })
                .collect(Collectors.toSet());
    }
}
