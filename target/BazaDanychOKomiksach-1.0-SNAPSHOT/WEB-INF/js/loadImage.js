function readURL(input) {
		console.log(input.target)
		
		if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $("#coverImg").attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}
    