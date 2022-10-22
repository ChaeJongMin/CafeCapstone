// 홈 버튼 이벤트 : 클릭 시 매장/포장 선택 가능한 mainpage 로드
$('#home').on('click', function() {
   location.replace('./mainpage');
});
$('#menu').on('click', function() {
   location.replace('./menuManager');
});