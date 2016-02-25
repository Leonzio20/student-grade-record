package sgr.app.api.notification;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for notifications for student.
 *
 * @author leonzio
 */
@Entity
@Table(name = "notification")
public class Notification implements Serializable
{

   private static final long serialVersionUID = 7334663727763287504L;

   @Id
   @Column(name = "notification_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "received", nullable = false, updatable = false)
   private Date received;

   @Column(name = "title", length = 100, nullable = false, updatable = false)
   private String title;

   @Column(name = "content", length = 255, nullable = false, updatable = false)
   private String content;

   @Column(name = "student_id", nullable = false, updatable = false)
   private Long studentId;

   public static Notification create(String title, String content, Long studentId)
   {
      final Notification notif = new Notification();
      notif.setTitle(title);
      notif.setContent(content);
      notif.setStudentId(studentId);
      return notif;
   }

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public Date getReceived()
   {
      return received;
   }

   public void setReceived(Date received)
   {
      this.received = received;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public String getContent()
   {
      return content;
   }

   public void setContent(String content)
   {
      this.content = content;
   }

   public Long getStudentId()
   {
      return studentId;
   }

   public void setStudentId(Long studentId)
   {
      this.studentId = studentId;
   }

}
