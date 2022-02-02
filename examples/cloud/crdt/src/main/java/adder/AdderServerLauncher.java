package adder;

import adder.AdderCommands.AddRequest;
import adder.AdderCommands.AddResponse;
import adder.AdderCommands.GetRequest;
import adder.AdderCommands.GetResponse;
import io.activej.config.ConfigModule;
import io.activej.inject.module.Module;
import io.activej.inject.module.Modules;
import io.activej.launcher.Launcher;
import io.activej.launchers.crdt.rpc.CrdtRpcServerModule;
import io.activej.service.ServiceGraphModule;

import java.util.List;

public final class AdderServerLauncher extends Launcher {
	public static final List<Class<?>> MESSAGE_TYPES = List.of(GetRequest.class, GetResponse.class, AddRequest.class, AddResponse.class);

	@Override
	protected Module getModule() {
		return Modules.combine(
				ServiceGraphModule.create(),
				ConfigModule.create()
						.withEffectiveConfigLogger(),
				new CrdtRpcServerModule<Long, DetailedSumsCrdtState>() {
					@Override
					protected List<Class<?>> getMessageTypes() {
						return MESSAGE_TYPES;
					}
				},
				new AdderServerModule()
		);
	}

	@Override
	protected void run() throws Exception {
		awaitShutdown();
	}

	public static void main(String[] args) throws Exception {
		new AdderServerLauncher().launch(args);
	}
}
