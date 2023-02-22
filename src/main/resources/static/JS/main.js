
 const toggleBtn = document.querySelector('.navbar__toggleBtn');
const menu = document.querySelector('.navbar__menu');
const icons = document.querySelector('.navbar__icons');
const li = document.querySelector('.navbar__menu li');

toggleBtn.addEventListener('click', () => {
  menu.classList.toggle('active')
  icons.classList.toggle('active')
  li.classList.toggle('active')
  
})

/*
클래스명 container 변수명 $silderwrapp
클래스명 slider-container 변수면 $slidercontainer
클래스명 slide 변수명 $slide
이전 변수명 navprev
다음 변수명 navnext
 */

//문서의 내용이 모두 로드 되면 할일
//대상.addEventListener('이벤트종류',할일);
//DOMcontentLoaded
//할일 = 함수 = funtion(){실제로}

document.addEventListener('DOMContentLoaded',function(){

 // 변수 지정
var $slideWrap = document.querySelector('.container'),
	$slidercontainer = document.querySelector('.slider-container'),
	$slide = document.querySelectorAll('.slide'),
	$navprev = document.getElementById('prev'),
	$slideHeight = 0;
	$slideCount = $slide.length,
	$currentIndex = 0,
	$navnext = document.getElementById('next');
	//슬라이드의 높이 확인 하면 부모의 높이로 지정하기 - 대상.offsetHight
	for(var i=0;i<$slideCount;i++){
		if($slideHeight < $slide[i].offsetHeight){
			$slideHeight = $slide[i].offsetHeight;
		}
	}
	
	//$silderwrapp.style.height = '200px';
	console.log($slideHeight);
	$slideWrap.style.height = $slideHeight + 'px';
	$slidercontainer.style.height=$slideHeight + 'px';
	
	//슬라이드가 있으면 가로로 배열하기
	for(var a = 0; a < $slideCount; a++){
		$slide[a].style.left = a * 100 + '%';		
	}
	//슬아이드 이동 함수
	function goToSliede(idx){
		$slidercontainer.style.left = -100 * idx +'%';
		$slidercontainer.classList.add('animated');
		$currentIndex = idx;
	}

	//버튼기능 업데이트 함수
	//버튼을 클릭하면 슬라이드 이동시키기
	//다음 버튼을 클릭하면 할일, 이전 버튼을 클릭하면 할일.
	$navprev.addEventListener('click',function(){
		//goToSlieder($currentIndex - 1);
		if($currentIndex == 0){
			//$navNext.classList.add('disabled');
			goToSliede($slideCount - 1);
		}else{
			//$navNext.classList.remove('disabled');
			goToSliede($currentIndex -1);
		}
		
	});
	$navnext.addEventListener('click',function(){
		//goToSlieder($currentIndex + 1);
		/*
		마지막이라면 $navNext 안보이도록, 아니면 $navNext 다시 나타나도록
		*/		
		if($currentIndex == $slideCount -1){
			//$navNext.classList.add('disabled');
			goToSliede(0);
		}else{
			//$navNext.classList.remove('disabled');
			goToSliede($currentIndex+1);
		}
	});
	//자동 슬라이드
	
});//domcontentloaded