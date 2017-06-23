package me.borawski.hcf;import java.io.File;import java.util.UUID;import org.bukkit.ChatColor;import org.bukkit.command.CommandExecutor;import org.bukkit.configuration.file.YamlConfiguration;import org.bukkit.plugin.java.JavaPlugin;import me.borawski.hcf.api.FileHandler;import me.borawski.hcf.api.LangHandler;import me.borawski.hcf.command.CustomCommandHandler;import me.borawski.hcf.command.commands.AchievementCommand;import me.borawski.hcf.command.commands.BanCommand;import me.borawski.hcf.command.commands.FStatCommand;import me.borawski.hcf.command.commands.FriendsCommand;import me.borawski.hcf.command.commands.InfoCommand;import me.borawski.hcf.command.commands.KothCommand;import me.borawski.hcf.command.commands.ManualCommand;import me.borawski.hcf.command.commands.RankCommand;import me.borawski.hcf.command.commands.SeasonCommand;import me.borawski.hcf.command.commands.SettingsCommand;import me.borawski.hcf.connection.MongoWrapper;import me.borawski.hcf.listener.ListenerManager;import me.borawski.hcf.listener.PlayerListener;import me.borawski.hcf.manual.ManualManager;import me.borawski.hcf.punishment.PunishmentHandler;import me.borawski.hcf.session.AchievementManager;import me.borawski.hcf.session.SessionHandler;import me.borawski.hcf.util.ManualUtil;import me.borawski.koth.Plugin;import me.finestdev.components.Components;/** * Created by Ethan on 3/8/2017. */public class Core extends JavaPlugin implements CommandExecutor {    private static final UUID CONSOLE = UUID.fromString("6afde421-9fbf-4dab-a9c3-87698280b2c5");    /**     * Instance     */    private static Core instance;    /**     * Variables     */    private MongoWrapper mongoWrapper;    private ListenerManager listenerManager;    private CustomCommandHandler customCommandHandler;    private PunishmentHandler punishmentHandler;    private SessionHandler sessionHandler;    private AchievementManager achievementManager;    private ManualManager manualManager;    private static LangHandler lang;    private static FileHandler config;    /**     * Components     */    private Components components;    /**     * Koth     */    private Plugin koth;    @Override    public void onEnable() {        instance = this;        saveDefaultConfig();        saveResource("lang.yml", false);        config = new FileHandler(getConfig());        lang = new LangHandler(YamlConfiguration.loadConfiguration(new File(getDataFolder(), "lang.yml")));        this.mongoWrapper = new MongoWrapper();        this.punishmentHandler = new PunishmentHandler();        this.sessionHandler = new SessionHandler();        this.listenerManager = new ListenerManager();        listenerManager.addListener(new PlayerListener());        listenerManager.registerAll();        customCommandHandler = new CustomCommandHandler();        registerCommands();        this.achievementManager = new AchievementManager();        this.manualManager = new ManualManager();        registerManuals();        this.components = new Components();        components.onEnable();        this.koth = new Plugin(this);        koth.onEnable();    }    /**     * Gets the singleton instance of the Core class.     *      * @return     */    public static Core getInstance() {        return instance;    }    public void registerCommands() {        customCommandHandler.registerCommand(new AchievementCommand());        customCommandHandler.registerCommand(new BanCommand());        customCommandHandler.registerCommand(new FriendsCommand());        customCommandHandler.registerCommand(new FStatCommand());        customCommandHandler.registerCommand(new InfoCommand());        customCommandHandler.registerCommand(new KothCommand());        customCommandHandler.registerCommand(new ManualCommand());        customCommandHandler.registerCommand(new RankCommand());        customCommandHandler.registerCommand(new SeasonCommand());        customCommandHandler.registerCommand(new SettingsCommand());    }    public void registerManuals() {        ManualUtil.initializeManuals(manualManager.getManualMap());    }    /**     * Gets the instance of MongoDB maintained by the plugin.     *      * @return     */    public MongoWrapper getMongoWrapper() {        return mongoWrapper;    }    /**     * Gets the instance of the listener manager. Used to add more listeners or     * update existing ones.     *      * @return     */    public ListenerManager getListenerManager() {        return listenerManager;    }    public CustomCommandHandler getCommandHandler() {        return customCommandHandler;    }    public PunishmentHandler getPunishmentHandler() {        return punishmentHandler;    }    public SessionHandler getSessionHandler() {        return sessionHandler;    }    public AchievementManager getAchievementManager() {        return achievementManager;    }    public ManualManager getManualManager() {        return manualManager;    }    public static LangHandler getLangHandler() {        return lang;    }    public static FileHandler getConfigHandler() {        return config;    }    public String getPrefix() {        return ChatColor.DARK_RED + "" + ChatColor.BOLD + "DesireHCF" + ChatColor.RESET + "" + ChatColor.GRAY + " ";    }    public static UUID getConsoleUUID() {        return CONSOLE;    }}