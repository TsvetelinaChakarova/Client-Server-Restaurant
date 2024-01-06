package bg.restaurant.systems.software.integration.design.command.handlers;

@FunctionalInterface
public interface CommandHandler {
    String execute();
}
