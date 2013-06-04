package enhancedportals.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import org.bouncycastle.util.Arrays;

import com.google.common.primitives.Bytes;

import enhancedportals.lib.Reference;

public abstract class PacketEnhancedPortals
{
    enum Type
    {
        REQUEST_DATA(PacketRequestData.class),
        GUI(PacketGui.class),
        PORTAL_MODIFIER_UPDATE(PacketPortalModifierUpdate.class),
        NETHER_PORTAL_UPDATE(PacketNetherPortalUpdate.class),
        PORTAL_MODIFIER_UPGRADE(PacketPortalModifierUpgrade.class),
        AUTOMATIC_DIALLER_UPDATE(PacketAutomaticDiallerUpdate.class),
        DIAL_DEVICE_UPDATE(PacketDialDeviceUpdate.class),
        BASIC_DIAL_DEVICE_UPDATE(PacketBasicDialDeviceUpdate.class);

        private Class<? extends PacketEnhancedPortals> packetType;

        private Type(Class<? extends PacketEnhancedPortals> clas)
        {
            packetType = clas;
        }

        PacketEnhancedPortals make()
        {
            try
            {
                return packetType.newInstance();
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public static Packet250CustomPayload makePacket(PacketEnhancedPortals packet)
    {
        byte[] packetData = packet.generatePacket();

        return new Packet250CustomPayload(Reference.MOD_ID, Bytes.concat(new byte[] { packet.getID() }, packetData));
    }

    public static PacketEnhancedPortals readPacket(INetworkManager network, byte[] payload)
    {
        try
        {
            int type = payload[0];
            Type eType = Type.values()[type];
            byte[] data = Arrays.copyOfRange(payload, 1, payload.length);

            return eType.make().consumePacket(data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private Type type;

    public PacketEnhancedPortals()
    {
        for (Type t : Type.values())
        {
            if (t.packetType == getClass())
            {
                type = t;
                break;
            }
        }

        if (type == null)
        {
            throw new RuntimeException("Can not create an unregistered packet!");
        }
    }

    public abstract PacketEnhancedPortals consumePacket(byte[] data);

    public abstract void execute(INetworkManager network, EntityPlayer player);

    public abstract byte[] generatePacket(Object... data);

    public byte getID()
    {
        return (byte) type.ordinal();
    }
}