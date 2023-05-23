import io.activej.http.AsyncServlet;
import io.activej.http.StaticServlet;
import io.activej.http.loader.IStaticLoader;
import io.activej.inject.annotation.Provides;
import io.activej.launchers.http.HttpServerLauncher;
import io.activej.reactor.Reactor;

import java.util.concurrent.Executor;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

//[START EXAMPLE]
public final class SvelteApplicationLauncher extends HttpServerLauncher {
	@Provides
	Executor executor() {
		return newSingleThreadExecutor();
	}

	@Provides
	IStaticLoader staticLoader(Reactor reactor, Executor executor) {
		return IStaticLoader.ofClassPath(reactor, executor, "public");
	}

	@Provides
	AsyncServlet servlet(Reactor reactor, IStaticLoader staticLoader) {
		return StaticServlet.builder(reactor, staticLoader)
			.withIndexHtml()
			.build();
	}

	public static void main(String[] args) throws Exception {
		SvelteApplicationLauncher launcher = new SvelteApplicationLauncher();
		launcher.launch(args);
	}
}
//[END EXAMPLE]
