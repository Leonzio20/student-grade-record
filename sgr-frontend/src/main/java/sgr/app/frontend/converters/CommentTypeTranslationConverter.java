package sgr.app.frontend.converters;

import sgr.app.api.comment.CommentType;

/**
 * Converts {@link CommentType} enum to translated value.
 *
 * @author dawbes89
 */
public class CommentTypeTranslationConverter extends AbstractConverter<CommentType>
{

   @Override
   protected CommentType convertToObject(String value)
   {
      return CommentType.valueOf(value);
   }

   @Override
   protected String convertToString(Object object)
   {
      final CommentType commentType = (CommentType) object;
      String translate = translationService.translate(commentType.getLabel());
      return translate;
   }

}