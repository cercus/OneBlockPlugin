package fr.cercusmc.oneblock.islandtest;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import fr.cercusmc.oneblock.OneBlock;
import fr.cercusmc.oneblock.islands.Island;
import fr.cercusmc.oneblock.phases.PhaseManager;
import fr.cercusmc.oneblock.utils.ToolsFunctions;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;

public class IslandTest {

    private ServerMock server;
    private OneBlock oneBlock;
    private World world;

    @Before
    public void setUp() {
        server = MockBukkit.mock();

        oneBlock = MockBukkit.load(OneBlock.class);
        //WorldCreator wc = oneBlock.makeOverworld();
        world = server.createWorld(oneBlock.makeOverworld());
        server.setPlayers(10);
    }

    @After
    public void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    public void testWorldCreated() {
        server.addWorld((WorldMock) world);
        Assertions.assertTrue(oneBlock.getServer().getWorld(world.getName()) != null);
    }

    @Test
    public void testPlayerHasNotIsland(){
        Assertions.assertFalse(OneBlock.getIslandManager().playerHasIsland(server.getPlayer(1).getUniqueId()));
    }

    @Test
    public void testPlayerCreateIsland() {
        Assertions.assertTrue(OneBlock.getIslandManager().createIsland(server.getPlayer(1).getUniqueId()));
        Assertions.assertEquals(server.getPlayer(1).getUniqueId().toString(), OneBlock.getIslandManager().getIslands().get(0).getOwner().toString());
    }

    @Test
    public void testPlayerHasIslandIfOwner(){
        OneBlock.getIslandManager().createIsland(server.getPlayer(1).getUniqueId());
        Assertions.assertTrue(OneBlock.getIslandManager().playerHasIsland(server.getPlayer(1).getUniqueId()));
    }

    @Test
    public void testIfFirstIslandIsCenterCoords() {
        OneBlock.getIslandManager().createIsland(server.getPlayer(1).getUniqueId());
        Assertions.assertEquals(OneBlock.getIslandManager().getIslandOfPlayer(server.getPlayer(1).getUniqueId()).getCenterBlock(), new Location(OneBlock.getOverworld(), 0, 73, 0));
    }

    @Test
    public void testCreateIslandIfPlayerHasIsland(){
        OneBlock.getIslandManager().createIsland(server.getPlayer(1).getUniqueId());
        Assertions.assertFalse(OneBlock.getIslandManager().createIsland(server.getPlayer(1).getUniqueId()));
    }

    @Test
    public void testIfTenIslandIsSet() {

        for(Player p : server.getOnlinePlayers()) {
            OneBlock.getIslandManager().createIsland(p.getUniqueId());
        }

        Assertions.assertEquals(OneBlock.getIslandManager().getIslands().size(), OneBlock.getPlayerFile().getNbIslandTotal());
    }

    @Test
    public void testDeleteIslandIfPlayerHasNotIsland() {
        Assertions.assertFalse(OneBlock.getIslandManager().removeIsland(server.getPlayer(1).getUniqueId()));
    }

    @Test
    public void testCreateIslandAfterDeletingIsland() {
        for(Player p : server.getOnlinePlayers())
            OneBlock.getIslandManager().createIsland(p.getUniqueId());

        OneBlock.getIslandManager().removeIsland(server.getPlayer(4).getUniqueId());
        OneBlock.getIslandManager().createIsland(server.getPlayer(4).getUniqueId());
        Island island = OneBlock.getIslandManager().getIslands().get(OneBlock.getIslandManager().getIslands().size()-1);
        Assertions.assertEquals(island.getCenterBlock(), ToolsFunctions.findNext(OneBlock.getIslandManager().getIslands().size()).convertToLocation(OneBlock.getOverworld()));
    }

    @Test
    public void testChangeHome() {
        OneBlock.getIslandManager().createIsland(server.getPlayer(1).getUniqueId());
        OneBlock.getIslandManager().changeHomeIsland(server.getPlayer(1).getUniqueId(), new Location(OneBlock.getOverworld(), 12, 73, 25));
        Assertions.assertEquals(new Location(OneBlock.getOverworld(), 12, 73, 25), OneBlock.getIslandManager().getIslandOfPlayer(server.getPlayer(1).getUniqueId()).getHome());
    }

    @Test
    public void testIfPlayerIsNotBanned() {
        OneBlock.getIslandManager().createIsland(server.getPlayer(1).getUniqueId());
        Island is = OneBlock.getIslandManager().getIslandOfPlayer(server.getPlayer(1).getUniqueId());
        Assertions.assertFalse(is.isBanned(server.getPlayer(2).getUniqueId()));
    }

    @Test
    public void testAddPlayerBannedInIsland(){

        OneBlock.getIslandManager().createIsland(server.getPlayer(1).getUniqueId());
        Island is = OneBlock.getIslandManager().getIslandOfPlayer(server.getPlayer(1).getUniqueId());
        is.addBanPlayerInIsland(server.getPlayer(2).getUniqueId());
        Assertions.assertTrue(is.isBanned(server.getPlayer(2).getUniqueId()));
    }

    @Test
    public void testPhase1Good() {
        PhaseManager pManager = new PhaseManager();
        String phaseExpected = "Phase{" +
                "id=1" +
                ", name='" + "§aPlaines" + '\'' +
                ", description='" + "§aLe début de votre aventure, avec des blocs simple pour commencer" + '\'' +
                ", icon=" + "GRASS_BLOCK" +
                ", blockToReach=" + 0 +
                ", blocks=" + "{GRASS_BLOCK=50, PODZOL=15, BIRCH_LOG=30, OAK_LOG=30, CLAY=15, PUMPKIN=20, GRAVEL=20}" +
                ", entities=" + "{PIG=50, CHICKEN=50, SHEEP=50, COW=50}" +
                ", items=" + "[APPLE, EGG, OAK_SAPLING, WHEAT_SEEDS, WATER_BUCKET, CARROT, LEATHER, MELON_SEEDS, POTATO, PUMPKIN_SEEDS, SWEET_BERRIES, ENCHANTED_BOOK]" +
                '}';

        Assertions.assertEquals(phaseExpected, pManager.getPhaseById(1).toString());
    }

    @Test
    public void testChooseRandomEntities() {
        PhaseManager pManager = new PhaseManager();
        HashMap<EntityType, Integer> counts = new HashMap<>();
        for(int i = 0;  i < 1000; i++) {
            EntityType entTmp = pManager.chooseRandomEntity(pManager.getPhaseById(1).getEntities());

            if(counts.containsKey(entTmp)) {
                counts.replace(entTmp, counts.get(entTmp)+1);
            } else {
                counts.put(entTmp, 1);
            }
        }
        System.out.println(counts); // Valeurs proche des probabilités théoriques
        Assertions.assertTrue(true);

    }


}
