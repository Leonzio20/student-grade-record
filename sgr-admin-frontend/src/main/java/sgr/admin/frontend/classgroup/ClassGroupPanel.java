package sgr.admin.frontend.classgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.commons.frontend.AbstractPanel;
import sgr.commons.frontend.EditablePanel;

/**
 * @author leonzio
 */
@Controller
public class ClassGroupPanel extends AbstractPanel<ClassGroup> implements EditablePanel<ClassGroup>
{

   private static final long serialVersionUID = 1665393811406612606L;

   @Autowired
   private ClassGroupService classGroupService;

   @Override
   public void create()
   {
      classGroupService.create(entity);
      entities = classGroupService.search();
      entity = new ClassGroup();
   }

   @Override
   public void update(ClassGroup object)
   {
      // nothing
   }

   @Override
   public void remove(Long id)
   {
      classGroupService.remove(id);
      entities = classGroupService.search();
   }

   @Override
   public void init()
   {
      entity = new ClassGroup();
   }

   @Override
   public void onLoad()
   {
      entities = classGroupService.search();
   }

}
