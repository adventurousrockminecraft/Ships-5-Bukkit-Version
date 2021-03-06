package MoseShipsBukkit.Listeners.ShipsCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import MoseShipsBukkit.Listeners.CommandLauncher;
import MoseShipsBukkit.ShipTypes.VesselType;
import MoseShipsBukkit.StillShip.Vessel;
import MoseShipsBukkit.Utils.MaterialItem;
import MoseShipsBukkit.Utils.ConfigLinks.MaterialsList;

public class Developer extends CommandLauncher{

	public Developer() {
		super("Developer", "", "All root commands", null, false, true);
	}

	@Override
	public void playerCommand(Player player, String[] args) {
	}

	@Override
	public void consoleCommand(ConsoleCommandSender sender, String[] args) {
		if (args.length == 1){
			sender.sendMessage(ChatColor.GOLD + "/ships developer loadedVessels");
			sender.sendMessage(ChatColor.GOLD + "/ships developer Vesseltypes");
			sender.sendMessage(ChatColor.GOLD + "/ships developer materialsList");
			sender.sendMessage(ChatColor.GOLD + "/ships developer ramMaterials");
			
			sender.sendMessage(ChatColor.GOLD + "/ships developer all");
		}else{
			if (args[1].equalsIgnoreCase("loadedVessels")){
				displayLoadedVessels(sender);
			}
			if (args[1].equalsIgnoreCase("Vesseltypes")){
				displayVesselTypes(sender);
			}
			if (args[1].equalsIgnoreCase("materialsList")){
				displayMaterialsList(sender);
			}
			if (args[1].equalsIgnoreCase("ramMaterials")){
				displayRAMMaterialsList(sender);
			}
			if (args[1].equalsIgnoreCase("all")){
				sender.sendMessage("-----[LoadedVessels]-----");
				displayLoadedVessels(sender);
				sender.sendMessage("-----[Types]-----");
				displayVesselTypes(sender);
				sender.sendMessage("-----[Materials]-----");
				displayMaterialsList(sender);
				sender.sendMessage("-----[RAM]-----");
				displayRAMMaterialsList(sender);
			}
		}
	}
	
	public void displayMaterialsList(ConsoleCommandSender sender){
		sender.sendMessage("<Name> | <Value>");
		MaterialsList list = MaterialsList.getMaterialsList();
		for(MaterialItem item : list.getMaterials()){
			sender.sendMessage(item.getMaterial().name() + " | " + item.getData());
		}
		sender.sendMessage("Total number of Materials in Materials List: " + list.getMaterials().size());
	}
	
	public void displayRAMMaterialsList(ConsoleCommandSender sender){
		sender.sendMessage("<Name> | <Value>");
		MaterialsList list = MaterialsList.getMaterialsList();
		for(MaterialItem item : list.getRamMaterials()){
			sender.sendMessage(item.getMaterial() + " | " + item.getData());
		}
		sender.sendMessage("Total number of Materials in RAM Materials List: " + list.getMaterials().size());
	}

	public void displayLoadedVessels(ConsoleCommandSender sender){
		sender.sendMessage("<Name> | <Type> | <Owner> | <Location>");
		for(Vessel vessel : Vessel.getVessels()){
			sender.sendMessage(vessel.getName() + " | " + vessel.getVesselType().getName() + " | " + vessel.getOwner().getName() + " | " + (int)vessel.getTeleportLocation().getX() + "," + (int)vessel.getTeleportLocation().getY() + "," + (int)vessel.getTeleportLocation().getZ() + "," + vessel.getTeleportLocation().getWorld().getName());
		}
		sender.sendMessage("Total number of Vessels loaded: " + Vessel.getVessels().size());
		return;
	}
	
	public void displayVesselTypes(ConsoleCommandSender sender){
		sender.sendMessage("<Type> | <Normal speed>");
		for(VesselType vessel : VesselType.values()){
			sender.sendMessage(vessel.getName() + " | " + vessel.getDefaultSpeed());
		}
		return;
	}
	
}
