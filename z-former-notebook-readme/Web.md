```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script src="js/bac.js"></script>
		<script src="js/hero.js"></script>
		<script src="js/ENEMY.js"></script>
		<script src="js/bullet.js"></script>
		<style type="text/css">
			*{
				margin: 0;
				padding: 0;
			}
			body{
				background-image:url(img/bg.png);
				background-repeat: no-repeat;
				background-size: 100% 100%;
			}
			#game_box{
				width: 480px;
				height: 850px;
				margin: 0 auto;
				position: relative;
			}
			#btnstart{
				position: absolute;
				width: 200px;
				height: 35px;
				bottom: 100px;
				left: 50%;
				background-color: steelblue;
				color: white;
				border-radius: 5px;
				border: none;
				transform: translateX(-50%);
				cursor: pointer;
			}
		</style>
	</head>
	<body>
		<div id="game_box">
			<canvas id="game" width="480px" height="850px"></canvas>
			<button id="btnstart">开始游戏</button>
		</div>
		<script type="text/javascript">
			var game = document.getElementById('game');
			//获取画布
			var gamectx = game.getContext('2d')//获取二级平面，通过画布获取画笔
			//开始游戏之前需要加载对应的资源
			//定义一个集合，用于存放需要加载的数据
			var res = [
				"./img/background.png",
				"./img/hero1.png",//飞机1
				"./img/hero2.png",//飞机2
				"./img/bullet3.png",//子弹
				"./img/enemy0.png",//敌方飞机
				"./img/enemy1.png",
				"./img/enemy2.png",
				"./img/enemy1_hit.png",//被击中
				"./img/enemy2_hit.png",
				"./img/enemy0_down1.png",//坠毁
				"./img/enemy0_down2.png",
				"./img/enemy0_down3.png",
				"./img/enemy0_down4.png",
				"./img/enemy1_down1.png",
				"./img/enemy1_down2.png",
				"./img/enemy1_down3.png",
				"./img/enemy1_down4.png",
				"./img/enemy2_down1.png",
				"./img/enemy2_down2.png",
				"./img/enemy2_down3.png",
				"./img/enemy2_down4.png",
				"./img/enemy2_down5.png",
				"./img/enemy2_down6.png",
				"./img/prop_type_0.png",//道具
				"./img/prop_type_1.png",
				"./img/bg1.jpg",
				"./img/bg2.jpg",
				"./img/bg3.jpg",
				"./img/bg4.jpg"
			]
			var resObj = [];//存放已经加载好的资源
			var loadcount = 0;//已加载资源的数量
			for(var i = 0;i < res.length;i++){
				var img= new Image();//为每一个图片创造一个图片对象
				img.src = res[i];
				resObj.push(img);
				img.onload = function(){
					//从服务器加载资源
					loadcount++;
					if(loadcount == res.length){
						//所有资源已经加载完成，可以开始游戏
						var bg = new Background();
						//创建实体化对象
						bg.draw(gamectx);
						var btnStart = document.querySelector("#btnstart");
						//获取开始游戏按钮
						btnStart.onclick = function(){
							startGame();
							//调用开始游戏的方法
							btnStart.remove();
							//移除方法
						}
					}
				}
			}
			
			var bg = null;
			//定义背景对象
			var hero = null;
			//定义玩家飞机对象
			var gameTimer = null;
			//游戏定时器的集合
			var enemylist = [];
			//敌方飞机集合
			var isaddenemy = true;
			//分数
			var score = 0;
			//子弹集合
			var bulletlist = [];
			
			//子弹定时器集合
			var firetimer = null;
			
			
			function startGame(){
				bg = new Background();
				//创建一个背景类的实例化对象
				hero = new Hero();
				fire();//开火
				gameTimer = setInterval(repaint, 30);//循环定时器
				game.onmousemove = function(event){
					//鼠标指针的坐标，让飞机随着鼠标移动
					hero.x = event.x - game.getBoundingClientRect().left - hero.width / 2;
					hero.y = event.y - game.getBoundingClientRect().top - hero.height / 2;
					if(hero.x > 480 - hero.width){
						hero.x = 480 - hero.width;
					}
					if(hero.x <= 0){
						hero.x = 0;
					}
					if(hero.y > 850 - hero.height){
						hero.y = 850 - hero.height;
					}
					if(hero.y <= 0){
						hero.y = 0;
					}
				}
			}
			
			
			//绘制
			function repaint(){
				bg.draw(gamectx);//画背景
				hero.draw(gamectx);//画飞机
				addenemy();//添加敌方飞机
				for(var i = 0;i < enemylist.length;i++){
					enemylist[i].draw(gamectx);
				}
				for(var i = 0; i < bulletlist.length;i++){
					bulletlist[i].draw(gamectx);
				}
			}
			
			
			function addenemy(){
				if(enemylist.length < 10 && isaddenemy == true){
					var count = 10 - enemylist.length;
					for(var i = 0; i < count ;i++){
						var e = new Enemy();
						//循环创建飞机对象
						enemylist.push(e);
						//添加飞机对象进入集合
						
					}
				}
			}
			
			function fire(){
				var audiolist = [];//音频
				for(var i = 0; i < 4;i++){
					var audio = document.createElement('audio');
					//创建一个音频
					audio.src = './img/bullet.mp3';
					audiolist.push(audio);
				}
				var index = 0;
				firetimer = setInterval(function(){
					audiolist[index].play();
					index++;
					if(index >= audiolist.length){
						index = 0;
					}
					if(hero.isdoublebuff == true){
						var b_left = new Bullet(hero.x, hero.y);
						var b_right = new Bullet(hero.x, hero.y);
						//定义两边子弹的位置
						b_left.x = hero.x + hero.width / 4 - b_left.width / 2;
						b_right.x = hero.x + hero.width * 3 /4 - b_right.width / 2;
						bulletlist.push(b_left);
						bulletlist.push(b_right);
					}else{
						var b = new Bullet(hero.x, hero.y);
						b.x = hero.x + hero.width / 2 - b.width / 2
						bulletlist.push(b);
					}
				}, 250)
			}
		</script>
	</body>
</html>

```

```javascript
class Background{
	//构造函数
	constructor() {
	    this.x = 0;
		this.y = -850;
		//取25~28之间的随机数
		var temp = 25 + parseInt(Math.random()*5);
		if(temp == 29){
			temp = 0;
		}
		this.img = resObj[temp];
		this.width = 480;
		this.height = 850 * 2;
	}
	//draw 绘画 绘制资源
	draw(gamectx){
		this.move();
		gamectx.drawImage(this.img, this.x, this.y, this.width, this.height)
	}
	move(){
		this.y = this.y + 1;
		//如果纵坐标大于等于0，就从-850重新开始
		if(this.y >= 0){
			this.y = -850;
		}
	}
}
```

```javascript
class Hero {
	constructor() {
		//构造函数
		this.x = 220;
		this.y = 600;
		this.img = resObj[1];
		this.imgindex = 0;
		//找到飞机，查看大小
		//太大了，减半
		this.width = this.img.width / 2;
		this.height = this.img.height / 2;
		this.isdoublebuff = true;
		//判断飞机是否具有双排子弹
	}
	draw(gamectx){
		this.imgindex++;
		if(this.imgindex >= 10){
			this.imgindex = 0;
		}
		if(this.imgindex < 5){
			this.img = resObj[1];
		}else{
			this.img = resObj[2];
		}
		gamectx.drawImage(this.img, this.x, this.y, this.width, this.height)
	}
}
```

```javascript
class Enemy{
	constructor() {
		//随机出现敌机
		var temp = parseInt(Math.random() * 100);
		if(temp < 82){
			//百分之八十二的概率是小飞机
			this.type = 0;
			this.img = resObj[4];
			this.speed = 3 + parseInt(Math.random() * 4);
			this.life = 1;//生命值
		}else if(temp < 97){
			//百分之十五的概率使中飞机
			this.type = 1;
			this.img = resObj[5];
			this.speed = 2 + parseInt(Math.random() * 4);
			this.life = 5;
		}else if(temp < 100){
			//百分之三的概率是大飞机
			this.type = 2;
			this.img = resObj[6];
			this.speed = 1 + parseInt(Math.random() * 4);
			this.life = 10;
		}
		this.width = this.img.width;
		this.height = this.img.height;
		this.x = parseInt(Math.random() * (480 - this.width));
		this.y = 0 - this.height;
		this.totalife = this.life;
		//总血量
	}
	draw(gamectx){
		this.move();
		gamectx.strokeStyle = 'yellow';
		gamectx.strokeRect(this.x, this.y - 8, this.width, 6);//实心,总血量矩形
		gamectx.fillStyle = 'red';
		gamectx.fillRect(this.x, this.y - 8, this.life/this.totalife*this.width, 6);//画矩形,红色部分,空心，当前血量矩形
		gamectx.drawImage(this.img, this.x, this.y, this.width, this.height);
	}
	move(){
		this.y = this.y + this.speed;
		//飞机移动到屏幕外，就删除
		if(this.y > 850){
			//在enemylist中删除
			var index = enemylist.indexOf(this);//找到集合中当前飞机下标
			if(index != -1){
				//如果当前飞机存在，则删除
				enemylist.splice(index, 1);
			}
		}
	}
	isDie(){
		if(this.life <= 0){
			//飞机死亡
			var index = enemylist.indexOf(this);
			if(index != -1){
				enemylist.splice(index, 1);
			}
			//统计分数
			//小飞机10分 中飞机三十分 大飞机100分
			if(this.type == 0){
				score += 10;
			}else if(this.type == 1){
				score += 30;
			}else if(this.type == 2){
				score += 100;
			}
			//添加爆炸动画
			
		}
	}
}
```

```javascript
class Bullet {
	constructor(x, y) {
		this.x = x;
		this.y = y;
		this.img = resObj[3];
		this.width = this.img.width / 4;
		this.height = this.img.height / 4;
	}
	draw(gamectx){
		this.move();
		gamectx.drawImage(this.img, this.x, this.y, this.width, this.height)
	}
	move(){
		this.y = this.y - 40;
		if(this.y < 0){
			//子弹跑到屏幕外
			var index = bulletlist.indexOf(this);
			if(index != -1){
				bulletlist.splice(index, 1);
			}
		}
	}
}
```


