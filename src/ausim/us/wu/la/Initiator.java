// Aus was here.
package ausim.us.wu.la;
import com.wurmonline.server.items.*;
import org.gotti.wurmunlimited.modloader.ReflectionUtil;
import org.gotti.wurmunlimited.modloader.interfaces.ItemTemplatesCreatedListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Initiator implements WurmServerMod, ItemTemplatesCreatedListener
{
    private static final Logger logger = Logger.getLogger(Initiator.class.getName());

    @Override
    public void onItemTemplatesCreated()
    {
        try
        {
            final ItemTemplate[] itemTemplates = ItemTemplateFactory.getInstance().getTemplates();
            final Field transportable = ReflectionUtil.getField(Class.forName("com.wurmonline.server.items.ItemTemplate"), "isTransportable");

            for (final ItemTemplate itemTemplate : itemTemplates)
                if (itemTemplate.getTemplateId() == ItemList.altarGold || itemTemplate.getTemplateId() == ItemList.altarSilver
                        || itemTemplate.getTemplateId() == ItemList.altarWood || itemTemplate.getTemplateId() == ItemList.altarStone)
                    ReflectionUtil.setPrivateField(itemTemplate, transportable, true);

        }
        catch (final ClassNotFoundException | NoSuchFieldException | IllegalAccessException ex)
        {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
    }
}