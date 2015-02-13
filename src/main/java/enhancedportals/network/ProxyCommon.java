package enhancedportals.network;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import enhancedportals.block.BlockDimensionalBridgeStabilizerController;
import enhancedportals.block.BlockFrame;
import enhancedportals.block.BlockFrameController;
import enhancedportals.block.BlockPortal;
import enhancedportals.item.ItemLocationCard;
import enhancedportals.item.ItemWrench;
import enhancedportals.portal.PortalMap;
import enhancedportals.tile.TileDimensionalBridgeStabilizerController;
import enhancedportals.tile.TileFrame;
import enhancedportals.tile.TileFrameController;
import enhancedportals.tile.TilePortal;
import enhancedportals.util.CreativeTabEP;

public class ProxyCommon {
    public static final CreativeTabs creativeTab = new CreativeTabEP();

    public static final BlockPortal portal = new BlockPortal("portal");
    public static final BlockFrame portalFrame = new BlockFrame("frame.frame");
    public static final BlockFrameController portalController = new BlockFrameController("frame.controller");
    public static final BlockDimensionalBridgeStabilizerController dbsController = new BlockDimensionalBridgeStabilizerController("dbs");
    
    public static final ItemWrench wrench = new ItemWrench("wrench");
    public static final ItemLocationCard locationCard = new ItemLocationCard("location_card");
    
    public PortalMap portalMap = new PortalMap();
    
    public void pre(File configFile) {
        GameRegistry.registerBlock(portal, "portal");
        GameRegistry.registerBlock(portalFrame, "frame");
        GameRegistry.registerBlock(portalController, "portal_controller");
        GameRegistry.registerBlock(dbsController, "dbs_controller");
        
        GameRegistry.registerTileEntity(TilePortal.class, "portal");
        GameRegistry.registerTileEntity(TileFrame.class, "portal_frame");
        GameRegistry.registerTileEntity(TileFrameController.class, "portal_controller");
        GameRegistry.registerTileEntity(TileDimensionalBridgeStabilizerController.class, "dbs_controller");
        
        GameRegistry.registerItem(wrench, "wrench");
        GameRegistry.registerItem(locationCard, "location_card");
    }
    
    public void init() {
        //NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }
    
    public void post() {
        
    }
}