package ch.collen.preterbackendserver.db.document;

import org.springframework.data.annotation.Id;

public record Community(@Id String id, String name, String description, String image) {
}
