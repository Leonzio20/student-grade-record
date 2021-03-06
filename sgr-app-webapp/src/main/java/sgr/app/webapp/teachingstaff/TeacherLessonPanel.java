package sgr.app.webapp.teachingstaff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.lesson.Lesson;
import sgr.app.api.lesson.LessonQuery;
import sgr.app.api.lesson.LessonService;
import sgr.app.api.presence.Presence;
import sgr.app.api.presence.PresenceStatus;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentQuery;
import sgr.app.api.student.StudentService;
import sgr.app.api.teachingstaff.TeachingStaff;
import sgr.app.frontend.panels.AbstractPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dawbes89
 */
@Controller
public class TeacherLessonPanel extends AbstractPanel<Lesson>
{

	private static final long serialVersionUID = 6551034627669299579L;

	@Autowired
	private StudentService studentService;

	@Autowired
	private ClassGroupService classGroupService;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private LessonService lessonService;

	private TeachingStaff currentLoggedTeacher;

	private List<Student> students;

	private Student student;

	private ClassGroup classGroup;

	private List<ClassGroup> classes;

	private List<Student> selectedStudents;

	@Override
	public void init()
	{
		selectedStudents = new ArrayList<>();
		students = new ArrayList<>();
		student = new Student();
	}

	@Override
	public void onLoad()
	{
		entity = new Lesson();
		classes = classGroupService.search(ClassGroupQuery.EMPTY);
		currentLoggedTeacher = authenticationService.getCurrentUser();
		if (classGroup == null)
		{
			classGroup = currentLoggedTeacher.getPreceptorClass();
		}
		searchLessons();
	}

	public void searchLessons()
	{
		entities = lessonService.search(createQuery());
	}

	private LessonQuery createQuery()
	{
		final LessonQuery query = new LessonQuery();
		if (classGroup != null && classGroup.getId() != null)
		{
			query.setClassGroupId(classGroup.getId());
		}
		if (currentLoggedTeacher.getSchoolSubject() != null)
		{
			query.setSchoolSubject(currentLoggedTeacher.getSchoolSubject());
		}
		return query;
	}

	public void searchStudents()
	{
		final StudentQuery query = new StudentQuery();
		if (classGroup != null)
		{
			query.setClassGroupId(classGroup.getId());
		}
		students = studentService.search(query);
	}

	public void create()
	{
		entity.setSubjectNumber(entities.size() + 1);
		entity.setClassGroup(classGroup);
		entity.setSchoolSubject(currentLoggedTeacher.getSchoolSubject());
		entity.setIssuerName(currentLoggedTeacher.getFullName());
		entity = lessonService.create(entity, createPressences());
		onLoad();
		selectedStudents = new ArrayList<>();
	}

	private List<Presence> createPressences()
	{
		final List<Presence> presences = students.stream().map(Presence::createAbsent).collect(Collectors.toList());
		presences.stream().filter(presence -> selectedStudents.contains(presence.getStudent())).forEach(
				presence -> presence.setStatus(PresenceStatus.PRESENT));
		return presences;
	}

	public List<Student> getStudents()
	{
		return students;
	}

	public void setStudents(List<Student> students)
	{
		this.students = students;
	}

	public Student getStudent()
	{
		return student;
	}

	public void setStudent(Student student)
	{
		this.student = student;
	}

	public ClassGroup getClassGroup()
	{
		return classGroup;
	}

	public void setClassGroup(ClassGroup classGroup)
	{
		this.classGroup = classGroup;
	}

	public List<ClassGroup> getClasses()
	{
		return classes;
	}

	public void setClasses(List<ClassGroup> classes)
	{
		this.classes = classes;
	}

	public TeachingStaff getCurrentLoggedTeacher()
	{
		return currentLoggedTeacher;
	}

	public void setCurrentLoggedTeacher(TeachingStaff currentLoggedTeacher)
	{
		this.currentLoggedTeacher = currentLoggedTeacher;
	}

	public List<Student> getSelectedStudents()
	{
		return selectedStudents;
	}

	public void setSelectedStudents(List<Student> selectedStudents)
	{
		this.selectedStudents = selectedStudents;
	}

}
