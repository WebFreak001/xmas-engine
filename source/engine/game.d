module engine.game;

import std.stdio;
import std.string;
import std.datetime;

import core.thread;

import derelict.sdl2.sdl;

import engine.gfx.bitmap;

public import Mouse = engine.input.mouse;
public import Keyboard = engine.input.keyboard;

class Game
{
public:
	this(int width, int height, int scale, string title)
	{
		DerelictSDL2.load();
		SDL_Init(SDL_INIT_VIDEO);
		_window = SDL_CreateWindow(title.toStringz, SDL_WINDOWPOS_CENTERED,
			SDL_WINDOWPOS_CENTERED, width * scale, height * scale, SDL_WINDOW_SHOWN);
		_renderer = SDL_CreateRenderer(_window, -1, 0);
		_texture = SDL_CreateTexture(_renderer, SDL_PIXELFORMAT_ABGR8888,
			SDL_TEXTUREACCESS_STATIC, width, height);
		_screen = Bitmap(width, height);
	}

	void start()
	{
		double delta = 0;
		long sleep = 0;
		long next = 0;
		immutable int targetFPS = 60;
		immutable int skipms = 1000 / targetFPS;
		StopWatch sw;
		StopWatch bench;
		SDL_Event event;
		long benchUpdate, benchDraw;

		while (true)
		{
			sw.start();
			
			while (SDL_PollEvent(&event))
			{
				switch (event.type)
				{
				case SDL_MOUSEBUTTONUP:
				case SDL_MOUSEBUTTONDOWN:
					Mouse.state.buttons[event.button.button] = event.button.state == SDL_PRESSED;
					Mouse.state.position.x = event.button.x;
					Mouse.state.position.y = event.button.y;
					break;
				case SDL_MOUSEMOTION:
					Mouse.state.position.x = event.motion.x;
					Mouse.state.position.y = event.motion.y;
					break;
				case SDL_KEYDOWN:
				case SDL_KEYUP:
					Keyboard.state.keys[event.key.keysym.sym] = event.key.state == SDL_PRESSED;
					break;
				case SDL_QUIT:
					return;
				default: break;
				}
			}

			next += skipms;
			sleep = next - Clock.currAppTick.to!("msecs", long);
			if (sleep > 0)
				Thread.sleep(dur!"msecs"(sleep));

			bench.start();
			privateUpdate(delta);
			privateRender();
			bench.stop();
			benchUpdate = bench.peek.usecs;
			bench.reset();
			bench.start();
			renderToScreen();
			bench.stop();
			benchDraw = bench.peek.usecs;
			bench.reset();
			
			writeln("UPDATE: ", benchUpdate);
			writeln("DRAW:   ", benchDraw);

			sw.stop();
			delta = sw.peek.usecs * 0.0000001;
			sw.reset();
		}
	}

	abstract void update(double delta);

	abstract void render(Bitmap screen);

	Bitmap screen() @property
	{
		return _screen;
	}
	
	int width() @property
	{
		int w;
		SDL_GetWindowSize(_window, &w, null);
		return w;
	}
	
	int height() @property
	{
		int h;
		SDL_GetWindowSize(_window, null, &h);
		return h;
	}

private:
	void privateUpdate(double delta)
	{
		this.update(delta);
	}
	
	void renderToScreen()
	{
		SDL_UpdateTexture(_texture, null, _screen.pixels.ptr, cast(int)(_screen.width * Color.sizeof));

		SDL_RenderClear(_renderer);
		SDL_RenderCopy(_renderer, _texture, null, null);
		SDL_RenderPresent(_renderer);
	}

	void privateRender()
	{
		this.render(screen);
	}

	SDL_Window* _window;
	SDL_Renderer* _renderer;
	SDL_Texture* _texture;
	Bitmap _screen;
}
