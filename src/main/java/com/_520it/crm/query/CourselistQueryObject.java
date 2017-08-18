package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class CourselistQueryObject extends QueryObject {
	private Long schoolclassId;
	private Long schoolroomId;
	private Long teacherId;
}
